package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaMediaDiariaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		//MovimentacaoDao(em) O em(EntityManager) dentro da chamada do Movimenta��o dao � o que chamamos de Inje��o de depend�ncia;
		List<MediaComData> mediaDasMovimentacoes = new MovimentacaoDao(em).getMediaDiariaDasMovimentacoes(); 
		
		for (MediaComData resultado : mediaDasMovimentacoes) {
			System.out.println("A m�dia das Movimenta��es do dia " + resultado.getDia() + "/" + resultado.getMes() + "/" + resultado.getAno() + " �: " + resultado.getValor());
		}
	}

}
