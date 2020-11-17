package br.com.alura.jpa.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	/* Por convenção, o Hibernate ainda precisará usar o construtor padrão da categoria para instanciar seus objetos; então o criaremos sem argumentos na
	 * classe "Categoria" que não executará nenhuma ação.*/
	@Deprecated
	public Categoria() {
	}
	/* Para indicar que esse construtor será usado somente para a infraestrutura e não pelos desenvolvedores, escrevemos a anotação "@Deprecated"*/

	/* Precisaremos criar um construtor que receberá o "Nome()" */
	public Categoria(String nome) {
		super();
		this.nome = nome;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + " - " + id;
	}
	

}
