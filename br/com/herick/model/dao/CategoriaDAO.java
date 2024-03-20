package br.com.herick.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.herick.connection.ConnectionFactory;
import br.com.herick.model.Categoria;

public class CategoriaDAO {
	
	// Método para salvar uma categoria no banco de dados
	public Categoria save(Categoria categoria) {
		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			// Inicia uma transação com o banco de dados
			em.getTransaction().begin();
			// Persiste (salva) a nova categoria no banco de dados
			em.persist(categoria);
			// Confirma a transação, efetivamente aplicando as alterações no banco de dados
			em.getTransaction().commit();
		} catch (Exception error) {
			// Se ocorrer uma exceção, imprime o erro e reverte a transação
			System.err.println(error);
			em.getTransaction().rollback();
		} finally {
			// Independentemente do resultado, a conexão com o banco de dados é fechada
			em.close();
		}
		
		// Retorna a categoria
		return categoria;
	}
	
	// Método para atualizar uma categoria no banco de dados
	public Categoria update(Categoria categoria) {
		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			// Inicia uma transação com o banco de dados
			em.getTransaction().begin();
			
			// Verifica se a categoria já possui um ID (se já existe no banco de dados)
			if(categoria.getId() == null) {
				// Se não possui ID, significa que é uma nova categoria, então persiste (salva) no banco de dados
				em.persist(categoria);
			} else {
				// Se possui ID, significa que a categoria já existe no banco de dados, então realiza um merge (atualização)
				em.merge(categoria);
			}
			
			// Confirma a transação, efetivamente aplicando as alterações no banco de dados
			em.getTransaction().commit();
		} catch (Exception error) {
			// Se ocorrer uma exceção, imprime o erro e reverte a transação
			System.err.println(error);
			em.getTransaction().rollback();
		} finally {
			// Independentemente do resultado, a conexão com o banco de dados é fechada
			em.close();
		}
		
		// Retorna a categoria
		return categoria;
	}
	
	// Método para encontrar uma categoria pelo seu ID no banco de dados
	public Categoria findByID(Integer idParam) {

		// Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
		EntityManager em = new ConnectionFactory().getConnection();
		Categoria categoria = null;
		
		try {
			// Tenta encontrar a categoria pelo ID fornecido
			categoria = em.find(Categoria.class, idParam);
		} catch (Exception error) {
			// Se ocorrer uma exceção, imprime o erro
			System.err.println(error);
		} finally {
			// Independentemente do resultado, a conexão com o banco de dados é fechada
			em.close();
		}
		
		// Retorna a categoria encontrada ou null se não encontrada
		return categoria;
	}
	
	// Método pra encontrar todas as categorias no banco de dados
	public List<Categoria> findAll() {
		
	    // Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
	    EntityManager em = new ConnectionFactory().getConnection();
	    List<Categoria> categorias = null;
	    
	    try {
	        // Executa uma consulta JPQL para buscar todas as categorias
	        categorias = em.createQuery("from Categoria", Categoria.class).getResultList();
	    } catch(Exception error) {
	        // Em caso de erro, imprime a exceção
	        System.err.println(error);
	    } finally {
	        // Fecha a conexão com o banco de dados, independentemente do resultado
	        em.close();
	    }
	    
	    // Retorna a lista de categorias encontradas (pode ser null se ocorrer um erro)
	    return categorias;
	}

	// Método para excluir um registro no banco de dados
	public Categoria remove(Integer idParam) {
		
	    // Obtém uma conexão com o banco de dados usando a classe ConnectionFactory
	    EntityManager em = new ConnectionFactory().getConnection();
	    Categoria categoria = null;
	    
	    try {
	        // Busca a categoria pelo seu ID
	        categoria = em.find(Categoria.class, idParam);
	        
	        // Inicia uma transação com o banco de dados
	        em.getTransaction().begin();
	        
	        // Remove a categoria do banco de dados
	        em.remove(categoria);
	        
	        // Confirma a transação
	        em.getTransaction().commit();
	    } catch(Exception error) {
	        // Em caso de erro, imprime a exceção e reverte a transação
	        System.err.println(error);
	        em.getTransaction().rollback();
	    } finally {
	        // Fecha a conexão com o banco de dados, independentemente do resultado
	        em.close();
	    }
	    
	    // Retorna a categoria removida (pode ser null se ocorrer um erro ou se a categoria não for encontrada)
	    return categoria;
	}
	
}
