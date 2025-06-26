package service;

import model.Receita;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceitaService {

    private static final String PATH = "data/receitas.txt";

    public static void salvarReceita(Receita receita) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(String.join(";",
                    receita.getNomeMedico(),
                    receita.getCrm(),
                    receita.getNomePaciente(),
                    receita.getValidade().toString()
            ));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar receita: " + e.getMessage());
        }
    }

    public static List<Receita> carregarReceitas() {
        List<Receita> receitas = new ArrayList<>();  // CORRIGIDO

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    Receita r = new Receita(
                            partes[0],
                            partes[1],
                            partes[2],
                            LocalDate.parse(partes[3])
                    );
                    receitas.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar receitas: " + e.getMessage());
        }

        return receitas;
    }

    public static Receita buscarReceitaPorPaciente(String nomePaciente) {
        for (Receita r : carregarReceitas()) {
            if (r.getNomePaciente().equalsIgnoreCase(nomePaciente) && r.getValidade().isAfter(LocalDate.now())) {
                return r;
            }
        }
        return null;
    }
}
