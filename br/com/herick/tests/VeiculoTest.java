package br.com.herick.tests;

import java.util.List;
import java.util.Scanner;

import br.com.herick.model.Categoria;
import br.com.herick.model.Veiculo;
import br.com.herick.model.dao.VeiculoDAO;



public class VeiculoTest {
		
	private static final Scanner scanner = new Scanner(System.in);
	private static final VeiculoDAO dao = new VeiculoDAO();

	public static void main(String[] args) {
		boolean continuar = true;
	        
	    while (continuar) {
	    	System.out.println("\nMENU:");
	    	System.out.println("-------------------------------|");
	    	System.out.println("1 - Adicionar veículo");
	    	System.out.println("2 - Atualizar veículo");
	    	System.out.println("3 - Buscar veículo por ID");
	    	System.out.println("4 - Listar todos os veículos");
	    	System.out.println("5 - Remover veículo por ID");
	    	System.out.println("0 - Sair");
	    	System.out.println("-------------------------------|");
	    	System.out.print("Escolha uma opção: ");
	            
	    	int opcao = scanner.nextInt();
	        scanner.nextLine();
	            
	        switch (opcao) {
	        	case 1:
	        		adicionarVeiculo();
	                break;
	            case 2:
	            	atualizarVeiculo();
	                break;
	            case 3:
	            	buscarVeiculoPorID();
	                break;
	            case 4:
	            	listarTodosOsVeiculos();
	                break;
	            case 5:
	            	removerVeiculoPorID();
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
	    
	private static void adicionarVeiculo() {
		
	    Veiculo veiculo = new Veiculo();

	    System.out.print("Digite a marca do veículo: ");
	    String marca = scanner.nextLine();
	    veiculo.setMarca(marca);
	    
	    System.out.print("Digite o modelo do veículo: ");
	    String modelo = scanner.nextLine();
	    veiculo.setModelo(modelo);
	    
	    System.out.print("Digite o ano de fabricação do veículo: ");
	    int anoFabricacao = scanner.nextInt();
	    veiculo.setAnoFabricacao(anoFabricacao);
	    scanner.nextLine();
	    
	    System.out.print("Digite a cor do veículo: ");
	    String cor = scanner.nextLine();
	    veiculo.setCor(cor);
	    
	    System.out.print("Digite o valor do veículo: ");
	    double valor = scanner.nextDouble();
	    veiculo.setValor(valor);
	    scanner.nextLine();
	    
	    System.out.println("Digite a categoria do veículo: ");
	    int descricaoCategoria = Integer.parseInt(scanner.nextLine());
	    Categoria categoria = new Categoria();
	    categoria.setId(descricaoCategoria);
	    veiculo.setCategoria(categoria);

	    Veiculo veiculoSalvo = dao.save(veiculo);
	    if (veiculoSalvo != null) {
	    	System.out.println("-------------------------------|");
	        System.out.println("Veículo adicionado com sucesso:");
	        System.out.println(
	        		"Novo veículo adicionado " + 
	        		veiculo.getMarca()	+" "+ 
	        		veiculo.getModelo() +" "+
	        		veiculo.getAnoFabricacao() +" "+
	        		veiculo.getValor()
	        	);
	        System.out.println("-------------------------------|");
	    } else {
	        System.out.println("Não foi possível adicionar o veículo.");
	    }
	}

	    
	private static void atualizarVeiculo() {

		System.out.println("-------------------------------------------");
        System.out.print("Digite o ID do veículo que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Veiculo veiculo = dao.findByID(id);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.println("Digite os novos dados do veículo:");
        System.out.print("Marca: ");
        veiculo.setMarca(scanner.nextLine());
        System.out.print("Modelo: ");
        veiculo.setModelo(scanner.nextLine());
        System.out.print("Cor: ");
        veiculo.setCor(scanner.nextLine());
        System.out.println("Valor: ");
        veiculo.setValor(scanner.nextDouble());
        

        Veiculo veiculoAtualizado = dao.update(veiculo);
        if (veiculoAtualizado != null) {
            System.out.println("Veículo atualizado com sucesso!");
            System.out.println(
            		veiculo.getMarca() +" "+
            		veiculo.getModelo() +" "+
            		veiculo.getCor() +" "+
            		veiculo.getValor() +" "+
            		veiculo.getCategoria().getDescricao());
        } else {
            System.out.println("Falha ao atualizar o veículo.");
        }
	}
	    
	    private static void buscarVeiculoPorID() {
	        System.out.print("Digite o ID do veículo: ");
	        int id = scanner.nextInt();
	        scanner.nextLine(); // Limpar o buffer do scanner
	        
	        Veiculo veiculo = dao.findByID(id);
	        if (veiculo != null) {
	            System.out.println("Veículo encontrado:");
	            System.out.println("O veículo no id: " + id +", é o: " + veiculo.getMarca() + " "+ veiculo.getModelo());
	        } else {
	            System.out.println("Veículo não encontrado.");
	        }
	    }
	    
	    private static void listarTodosOsVeiculos() {
	        List<Veiculo> veiculos = dao.findAll();
	        if (!veiculos.isEmpty()) {
	            System.out.println("Lista de veículos:");
	            System.out.println("---------------------|");
	            for (Veiculo veiculo : veiculos) {
	                System.out.println(
	                		veiculo.getId() + " "+
	                		veiculo.getMarca() +" "+
	                		veiculo.getModelo()
	                		);
	                System.out.println("---------------------|");
	            }
	        } else {
	            System.out.println("Não há veículos cadastrados.");
	        }
	    }
	    
	    private static void removerVeiculoPorID() {
	        System.out.print("Digite o ID do veículo a ser removido: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();
	                
	        Veiculo veiculoRemovido = dao.remove(id);
	        if (veiculoRemovido != null) {
	            System.out.println("Veículo removido com sucesso:");
	            System.out.println("Veículo a ser removido: " + "id: " + veiculoRemovido.getId() +" marca "+ veiculoRemovido.getMarca() +" modelo "+ veiculoRemovido.getModelo());
	        } else {
	            System.out.println("Não foi possível remover o veículo. Veículo não encontrado.");
	        }
	    }
		
	}
	
