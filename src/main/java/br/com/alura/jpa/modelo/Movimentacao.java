package br.com.alura.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * O tipo movimentação será uma "String", e poderemos setar alguns padrões para
	 * recebermos como valor. Por exemplo, poderemos dizer que toda transação de
	 * entrada receberá "ENTRADA" e de saída será "SAIDA". Porém, será preciso
	 * validarmos constantemente para impedir que Strings inadequadas prejudiquem o
	 * resultado, o que não é interessante
	 */

	/*
	 * Para isso, teremos um recurso chamado "enum" que torna os tipos mais
	 * expressivos, o qual é fixado para podermos estabelecer que um atributo só
	 * poderá ter um conjunto específico de valores
	 */

	@Enumerated(EnumType.STRING) /* -> Diz para JPA que queremos salvar a String que caracteriza o "enum" */
	private TipoMovimentacao tipoMovimentacao;

	/*
	 * Utilizaremos o tipo "LocalDateTime" para salvarmos a data e o horário da
	 * movimentação.
	 */
	private LocalDateTime data;

	private String descricao;
	
	/*
	 * O valor será do tipo "BigDecimal", pois é mais específico do que o "Double"
	 * quando trabalhamos com dinheiro, o que exige maior precisão
	 */
	private BigDecimal valor;
	
	
	/* Devemos criar um relacionamento Muitos para Um entre a própria Conta e Movimentação*/
	@ManyToOne  //-> Anotação de Cardinalidade        
	private Conta conta;
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescrição(String descricao) {
		this.descricao = descricao;
	}

}
