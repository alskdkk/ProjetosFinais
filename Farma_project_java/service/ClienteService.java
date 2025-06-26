package service;

import model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static final String PATH = "data/clientes.txt";

    public static void salvarCliente(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(cliente.getNome() + ";" + cliente.getCpf());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    clientes.add(new Cliente(dados[0], dados[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public static Cliente buscarPorCpf(String cpf) {
        for (Cliente c : carregarClientes()) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}
