package app.dto;

import javax.persistence.Entity;

/**
 * Classe que representa ...
 * 
 * @author Jecson Matos
 * @version 1.0
 * @since 2019-05-30
 *
 */
 
 @Entity
public class CorSemana {

	private Integer id;

	private String descricao;

	public Integer getId() {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Construtor
	 **/
	public CorSemana (){
	}

}
