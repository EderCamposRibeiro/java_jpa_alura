package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamentoMovimentacaoCategoria {

	public static void main(String[] args) {
		
		/* A opção que fizemos aqui foi passarmos o "Nome()" pelo construtor, para que a categoria já seja criada com este.
		 * Isso evitaria erros em casos de não chamada do Stter, por exemplo.*/
		Categoria categoria = new Categoria("Viagem");
		//categoria.setNome("Viagem");
		
		Categoria categoria2 = new Categoria("Negócios");
		//categoria2.setNome("Negócios");
		
		Conta conta = new Conta();
		conta.setId(2L);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("Viagem à SP");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setValor(new BigDecimal(300.0));
		
		/* Utilizaremos um método estático da classe "Arrays" chamado "asList()", o qual criará uma lista de forma
		 * inline com os argumentos passados*/
		movimentacao.setCategorias(Arrays.asList(categoria, categoria2));
		movimentacao.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setDescricao("Viagem ao RJ");
		movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao2.setData(LocalDateTime.now());
		movimentacao2.setValor(new BigDecimal(400.0));		
		
		movimentacao2.setCategorias(Arrays.asList(categoria, categoria2));
		movimentacao2.setConta(conta);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(categoria);
		em.persist(categoria2);
		
		em.persist(movimentacao);
		em.persist(movimentacao2);
		em.getTransaction().commit();
		em.close();
		

	}

}
