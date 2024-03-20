package br.com.herick.tests;

import java.util.List;
import java.util.Scanner;

import br.com.herick.model.Categoria;
import br.com.herick.model.dao.CategoriaDAO;

public class CategoriaTest {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final CategoriaDAO dao = new CategoriaDAO();
    
    public static void main(String[] args) {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\nMENU:");
            System.out.println("-------------------------------|");
            System.out.println("1 - Adicionar categoria");
            System.out.println("2 - Atualizar categoria");
            System.out.println("3 - Buscar categoria por ID");
            System.out.println("4 - Listar todas as categorias");
            System.out.println("5 - Remover categoria por ID");
            System.out.println("0 - Sair");
            System.out.println("-------------------------------|");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    adicionarCategoria();
                    break;
                case 2:
                    atualizarCategoria();
                    break;
                case 3:
                    buscarCategoriaPorID();
                    break;
                case 4:
                    listarTodasAsCategorias();
                    break;
                case 5:
                    removerCategoriaPorID();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    private static void adicionarCategoria() {
        Categoria categoria = new Categoria();
        
        System.out.print("Digite a descrição da categoria: ");
        String descricao = scanner.nextLine();
        categoria.setDescricao(descricao);
        
        Categoria categoriaSalva = dao.save(categoria);
        if (categoriaSalva != null) {
            System.out.println("Categoria adicionada com sucesso:");
            System.out.println("ID: " + categoriaSalva.getId() + ", Descrição: " + categoriaSalva.getDescricao());
        } else {
            System.out.println("Não foi possível adicionar a categoria.");
        }
    }
    
    private static void atualizarCategoria() {
        System.out.print("Digite o ID da categoria que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Categoria categoria = dao.findByID(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }
        
        System.out.println("Digite a nova descrição da categoria: ");
        String descricao = scanner.nextLine();
        categoria.setDescricao(descricao);
        
        Categoria categoriaAtualizada = dao.update(categoria);
        if (categoriaAtualizada != null) {
            System.out.println("Categoria atualizada com sucesso!");
            System.out.println("ID: " + categoriaAtualizada.getId() + ", Nova Descrição: " + categoriaAtualizada.getDescricao());
        } else {
            System.out.println("Falha ao atualizar a categoria.");
        }
    }
    
    private static void buscarCategoriaPorID() {
        System.out.print("Digite o ID da categoria: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Categoria categoria = dao.findByID(id);
        if (categoria != null) {
            System.out.println("Categoria encontrada:");
            System.out.println("ID: " + categoria.getId() + ", Descrição: " + categoria.getDescricao());
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
    
    private static void listarTodasAsCategorias() {
        List<Categoria> categorias = dao.findAll();
        if (!categorias.isEmpty()) {
            System.out.println("Lista de categorias:");
            for (Categoria categoria : categorias) {
                System.out.println("ID: " + categoria.getId() + ", Descrição: " + categoria.getDescricao());
            }
        } else {
            System.out.println("Não há categorias cadastradas.");
        }
    }
    
    private static void removerCategoriaPorID() {
        System.out.print("Digite o ID da categoria a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();
                
        Categoria categoriaRemovida = dao.remove(id);
        if (categoriaRemovida != null) {
            System.out.println("Categoria removida com sucesso:");
            System.out.println("ID: " + categoriaRemovida.getId() + ", Descrição: " + categoriaRemovida.getDescricao());
        } else {
            System.out.println("Não foi possível remover a categoria. Categoria não encontrada.");
        }
    }
}
