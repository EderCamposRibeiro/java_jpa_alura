package br.com.alura.jpa.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class MovimentacaoDao {

	/*
	 * Separar as regras de neg�cio das l�gicas de persist�ncia, criando uma camada
	 * de persist�cia Com o uso do DAO criamos uma camada onde somente por meio dela
	 * � poss�vel acessar os dados de uma entidade espec�fica.
	 */

	/*
	 * Fazendo isso melhoramos a manutenabilidade do nosso c�digo j� que evitamos
	 * espalhar detalhes de persist�ncia pelas regras de neg�cio. Ao criarmos uma
	 * camada de persist�ncia, ao surgir um novo requisito saberemos exatamente onde
	 * ir.
	 */

//	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
//	= emf.createEntityManager();
	private EntityManager em;

	/*
	 * Inje��o de depend�ncia facilita o teste e facilita a manuten��o. Ao receber
	 * depend�ncias pelo construtor(ou por m�todos set), � poss�vel passar
	 * inst�ncias com comportamentos simulados para isolar a classe que ser� testada
	 */
	/*
	 * Inje��o de depend�ncias melhora a coes�o e separa as responsabilidades. J� que
	 * a classe injetada n�o precisa construir suas depend�ncias, n�o � necess�rio
	 * conhecer detalhes sobre o funcionamento de suas depend�ncias.
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
