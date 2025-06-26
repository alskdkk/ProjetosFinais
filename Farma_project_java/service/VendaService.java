package service;

import java.io.*;
import java.time.LocalDate;
import model.*;

public class VendaService {

    public static boolean venderProduto(Farmacia farmacia, Produto produto, String nomePaciente) {
        if (!produto.podeSerVendido()) {
            return false;
        }

        Receita receita = null;

        if (produto instanceof MedicamentoRestrito) {
            receita = ReceitaService.buscarReceitaPorPaciente(nomePaciente);
            if (receita == null || receita.getValidade().isBefore(LocalDate.now())) {
                System.out.println("Receita inválida ou não encontrada para o paciente.");
                return false;
            } else {
                ((MedicamentoRestrito) produto).apresentarReceita();
            }
        }

        farmacia.removerProduto(produto);
        System.out.println("Venda concluída com sucesso.");
        return true;
    }

    private static final String VENDAS_PATH = "data/vendas.txt";

    public static void registrarVenda(Venda venda) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VENDAS_PATH, true))) {
            writer.write(String.join(";",
                    venda.getCliente(),
                    venda.getFuncionario(),
                    String.valueOf(venda.getValorTotal()),
                    venda.getData().toString()
            ));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

}
