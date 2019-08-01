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
public class MesAgenda {

	private String id;

	private String value;

	private String anoValue;

	public String getId (){
		return this.id;
	}
	public void setId (String id) {
		this.id = id;
	}
	
	public String getValue (){
		return this.value;
	}
	
	public void setValue (String value) {
		this.value = value;
	}

	public String getAnoValue (){
		return this.anoValue;
	}
	
	public void setAnoValue (String anoValue) {
		this.anoValue = anoValue;
	}

	
	/**
	 * Construtor
	 **/
	public MesAgenda (){
	}

}
