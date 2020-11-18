package br.com.alura.jpa.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class MovimentacaoDao {

	/*
	 * Separar as regras de negócio das lógicas de persistência, criando uma camada
	 * de persistêcia Com o uso do DAO criamos uma camada onde somente por meio dela
	 * é possível acessar os dados de uma entidade específica.
	 */

	/*
	 * Fazendo isso melhoramos a manutenabilidade do nosso código já que evitamos
	 * espalhar detalhes de persistência pelas regras de negócio. Ao criarmos uma
	 * camada de persistência, ao surgir um novo requisito saberemos exatamente onde
	 * ir.
	 */

//	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
//	= emf.createEntityManager();
	private EntityManager em;

	/*
	 * Injeção de dependência facilita o teste e facilita a manutenção. Ao receber
	 * dependências pelo construtor(ou por métodos set), é possível passar
	 * instâncias com comportamentos simulados para isolar a classe que será testada
	 */
	/*
	 * Injeção de dependências melhora a coesão e separa as responsabilidades. Já que
	 * a classe injetada não precisa construir suas dependências, não é necessário
	 * conhecer detalhes sobre o funcionamento de suas dependências.
	 */

	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<MediaComData> getMediaDiariaDasMovimentacoes() {

		TypedQuery<MediaComData> query = em.createNamedQuery("mediaDiariaMovimentacoes", MediaComData.class);
		return query.getResultList();
	}

	public BigDecimal getSomaDasMovimentacoes() {

		String jpql = "select sum(m.valor) from Movimentacao m";

		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
		BigDecimal somaDasMovimentacoes = query.getSingleResult();
		return somaDasMovimentacoes;
	}

}
