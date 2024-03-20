package br.com.herick.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class ConnectionFactory {
	
	// EntityManagerFactory responsável por gerenciar as instâncias de EntityManager
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("veiculosPU");
	
	// Método para obter uma nova instância de EntityManager
	public EntityManager getConnection() {
		return emf.createEntityManager();
	}

}
