package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Farmaceutico;

public class FarmaceuticoService {

    private static final String PATH = "data/farmaceuticos.txt";

    public static void salvarFarmaceutico(Farmaceutico f) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(f.getNome() + ";" + f.getCrf());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar farmacêutico: " + e.getMessage());
        }
    }

    public static List<Farmaceutico> carregarFarmaceuticos() {
        List<Farmaceutico> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    lista.add(new Farmaceutico(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar farmacêuticos: " + e.getMessage());
        }
        return lista;
    }
}
