package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class TestaMediaDiariaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select new br.com.alura.jpa.modelo.MediaComData(avg(m.valor), day(m.data), month(m.data), year(m.data)) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
		
		/* O ideal � trabalhar com o tipo espec�fico!*/
//		Query query = em.createQuery(jpql);
//		List<Object[]> mediaDasMovimentacoes = query.getResultList();
		
		TypedQuery<MediaComData> query = em.createQuery(jpql, MediaComData.class);
		List<MediaComData> mediaDasMovimentacoes = query.getResultList();
		
		for (MediaComData resultado : mediaDasMovimentacoes) {
			System.out.println("A m�dia das Movimenta��es do dia " + resultado.getDia() + "/" + resultado.getMes() + "/" + resultado.getAno() + " �: " + resultado.getValor());
		}
	}

}