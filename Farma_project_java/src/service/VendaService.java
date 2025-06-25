package service;

import java.time.LocalDate;
import java.util.List;
import model.*;

public class VendaService {

    public static boolean venderProduto(Farmacia farmacia, Produto produto, Receita receita) {
        if (!produto.podeSerVendido()) return false;

        if (produto instanceof MedicamentoRestrito) {
            if (receita == null || receita.getValidade().isBefore(LocalDate.now())) {
                System.out.println("Receita inválida ou não apresentada.");
                return false;
            } else {
                ((MedicamentoRestrito) produto).apresentarReceita();
            }
        }

        farmacia.removerProduto(produto);
        return true;
    }
}
