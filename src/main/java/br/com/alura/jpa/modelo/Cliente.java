package br.com.alura.jpa.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String profissao;
	
	private String endereco;
	
	
	/* Sabemos que a conta do cliente não pode pertencer à mais ninguém, então cada uma é individual.
	 * Portanto, o relacinamento é UM PARA UM, ou seja, "@OneToOne".
	 * O "@OneToOne" presente na classe "Cliente", por padrão, não coloca essa restrição construente nas tabelas.
	 * Neste mesmo código, aplicaremos o comportamento através da anotação "@JoinColumn()" passando o atributo
	 * "unique" como "true", o que tornara única a chave estrangeira, impedindo outros relacionamentos além da "Conta"*/
	@JoinColumn(unique = true)
	@OneToOne
	private Conta Conta;
	/* O "@JoinColumn" somente possui efeito no momento da criação das tabelas, e não atua na atualização.*/

	public Conta getConta() {
		return Conta;
	}
	
	public void setConta(Conta conta) {
		Conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
}
