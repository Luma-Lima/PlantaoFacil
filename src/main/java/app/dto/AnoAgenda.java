package app.dto;

import java.io.Serializable;

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
public class AnoAgenda implements Serializable{

	private String id;

	private String value;

	public String getId (){
		return this.id;
	}

	public String getValue (){
		return this.value;
	}

	public void setId (String id){
		this.id = id;
	}

	public void setValue (String value){
		this.value = value;
	}

	/**
	 * Construtor
	 **/
	public AnoAgenda (){
	}

}
