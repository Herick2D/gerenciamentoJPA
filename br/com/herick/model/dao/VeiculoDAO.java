package br.com.herick.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.herick.connection.ConnectionFactory;
import br.com.herick.model.Veiculo;

public class VeiculoDAO {
	
	// Método para salvar um veículo no banco de dados
	public Veiculo save(Veiculo veiculo) {
		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		System.out.println(veiculo.getAnoFabricacao() +" "+ veiculo.getMarca() +" "+ veiculo.getModelo() +" "+ veiculo.getCor() +" "+ veiculo.getValor() +" "+ veiculo.getCategoria().getDescricao());
		
		try {
			// Inicia uma transação com o banco de dados
			em.getTransaction().begin();
			// Persiste (salva) o veículo no banco de dados
			em.persist(veiculo);
			// Confirma a transação, efetivamente aplicando as alterações no banco de dados
			em.getTransaction().commit();
		} catch(Exception error) {
			// Se ocorrer uma exceção, imprime o erro
			System.out.println(error);
			System.err.println("Rollback de Veiculos");
			// Reverte a transação
			em.getTransaction().rollback();
		} finally {
			// Fecha a conexão com o banco de dados, independentemente do resultado
			em.close();
		}
		
		// Retorna o veículo
		return veiculo;
	}
	
	// Método para atualizar um veículo no banco de dados
	public Veiculo update(Veiculo veiculo) {
		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			// Inicia uma transação com o banco de dados
			em.getTransaction().begin();
			// Verifica se o veículo já possui um ID (se já existe no banco de dados)
			if (veiculo.getId() == null) {
				// Se não possui ID, significa que é um novo veículo, então persiste (salva) no banco de dados
				em.persist(veiculo);
			} else {
				// Se possui ID, significa que o veículo já existe no banco de dados, então realiza um merge (atualização)
				em.merge(veiculo);
			}
			// Confirma a transação, efetivamente aplicando as alterações no banco de dados
			em.getTransaction().commit();
		} catch(Exception error) {
			// Se ocorrer uma exceção, imprime o erro
			System.out.println(error);
			// Reverte a transação
			em.getTransaction().rollback();
		} finally {
			// Fecha a conexão com o banco de dados, independentemente do resultado
			em.close();
		}
		
		// Retorna o veículo
		return veiculo;
	}
	
	// Método para encontrar um veículo pelo seu ID no banco de dados
	public Veiculo findByID(Integer idParam) {
		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		Veiculo veiculo = null;
		
		try {
			// Tenta encontrar o veículo pelo ID fornecido
			veiculo = em.find(Veiculo.class, idParam);
		} catch(Exception error) {
			// Se ocorrer uma exceção, imprime o erro
			System.err.println(error);
		} finally {
			// Fecha a conexão com o banco de dados, independentemente do resultado
			em.close();
		}
		
		// Retorna o veículo encontrado (ou null se não for encontrado)
		return veiculo;
	}
	
	// Método pra encontrar todos os veículos no banco de dados
	public List<Veiculo> findAll() {
		
	    // Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
	    EntityManager em = new ConnectionFactory().getConnection();
	    List<Veiculo> veiculos = null;
	    
	    try {
	        // Executa uma consulta JPQL para buscar todos os veículos
	        veiculos = em.createQuery("from Veiculo", Veiculo.class).getResultList();
	    } catch(Exception error) {
	        // Em caso de erro, imprime a exceção
	        System.err.println(error);
	    } finally {
	        // Fecha a conexão com o banco de dados, independentemente do resultado
	        em.close();
	    }
	    
	    // Retorna a lista de veículos encontrados (pode ser null se ocorrer um erro)
	    return veiculos;
	}
	
	// Método para excluir um registro no banco de dados
	public Veiculo remove(Integer idParam) {
	    // Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
	    EntityManager em = new ConnectionFactory().getConnection();
	    Veiculo veiculo = null;
	    
	    try {
	        // Busca o veículo pelo ID fornecido
	        veiculo = em.find(Veiculo.class, idParam);
	        
	        // Inicia uma transação com o banco de dados
	        em.getTransaction().begin();
	        
	        // Remove o veículo do banco de dados
	        em.remove(veiculo);
	        
	        // Confirma a transação, efetivamente aplicando as alterações no banco de dados
	        em.getTransaction().commit();
	    } catch(Exception error) {
	        // Em caso de erro, imprime a exceção
	        System.err.println(error);
	        // Reverte a transação
	        em.getTransaction().rollback();
	    } finally {
	        // Fecha a conexão com o banco de dados, independentemente do resultado
	        em.close();
	    }
	    
	    // Retorna o veículo removido (ou null se não for encontrado)
	    return veiculo;
	}
	
}
