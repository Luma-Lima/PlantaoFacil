package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
 * Classe que representa a tabela HOSPITAIS
 * @generated
 */
@Entity
@Table(name = "\"HOSPITAIS\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Hospitais")
public class Hospitais implements Serializable {

  /**
   * UID da classe, necessário na serialização
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * @generated
   */
  @Id
  @Column(name = "id", nullable = false, insertable=true, updatable=true)
  private java.lang.String id = UUID.randomUUID().toString().toUpperCase();

  /**
  * @generated
  */
  @Column(name = "cd_codigo_hsp", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Integer cd_codigo_hsp;

  /**
  * @generated
  */
  @Column(name = "nm_hospital_hsp", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.String nm_hospital_hsp;

  /**
   * Construtor
   * @generated
   */
  public Hospitais(){
  }


  /**
   * Obtém id
   * return id
   * @generated
   */
  
  public java.lang.String getId(){
    return this.id;
  }

  /**
   * Define id
   * @param id id
   * @generated
   */
  public Hospitais setId(java.lang.String id){
    this.id = id;
    return this;
  }

  /**
   * Obtém cd_codigo_hsp
   * return cd_codigo_hsp
   * @generated
   */
  
  public java.lang.Integer getCd_codigo_hsp(){
    return this.cd_codigo_hsp;
  }

  /**
   * Define cd_codigo_hsp
   * @param cd_codigo_hsp cd_codigo_hsp
   * @generated
   */
  public Hospitais setCd_codigo_hsp(java.lang.Integer cd_codigo_hsp){
    this.cd_codigo_hsp = cd_codigo_hsp;
    return this;
  }

  /**
   * Obtém nm_hospital_hsp
   * return nm_hospital_hsp
   * @generated
   */
  
  public java.lang.String getNm_hospital_hsp(){
    return this.nm_hospital_hsp;
  }

  /**
   * Define nm_hospital_hsp
   * @param nm_hospital_hsp nm_hospital_hsp
   * @generated
   */
  public Hospitais setNm_hospital_hsp(java.lang.String nm_hospital_hsp){
    this.nm_hospital_hsp = nm_hospital_hsp;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Hospitais object = (Hospitais)obj;
    if (id != null ? !id.equals(object.id) : object.id != null) return false;
    return true;
  }

  /**
   * @generated
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

}
