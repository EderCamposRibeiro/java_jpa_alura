package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQLMovimentacaoDeUmaCategoria {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";
		
		Categoria categoria = new Categoria();
		categoria.setId(2L);
		
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pCategoria", categoria);
		List<Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("");
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Data.....: " + movimentacao.getData());
			System.out.println("Tipo Mov.: " + movimentacao.getTipoMovimentacao());
			System.out.println("Valor....: R$ " + movimentacao.getValor());
			System.out.println("Cliente..: " + movimentacao.getConta().getTitular());
			System.out.println("Categoria: " + movimentacao.getCategorias());
			System.out.println("----------------------------------------------//-------------------------------");
		}

	}

}
