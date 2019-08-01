package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;
import cronapi.CronapiSearchable;


/**
 * Classe que representa a tabela AGENDA
 * @generated
 */
@Entity
@Table(name = "\"AGENDA\"" ,uniqueConstraints=@UniqueConstraint(columnNames={
"fk_local_Plantao" }))
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Agenda")
public class Agenda implements Serializable {

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
  @ManyToOne
  @JoinColumn(name="fk_horario_Escala", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Horario_Escala horario_Escala;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_medico", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Medico medico;

  /**
  * @generated
  */
  @Temporal(TemporalType.DATE)
  @Column(name = "dt_agenda_age", nullable = false, unique = false, insertable=true, updatable=true)
  @CronapiSearchable
  
  private java.util.Date dt_agenda_age;

  /**
  * @generated
  */
  @Column(name = "cd_status_age", nullable = true, unique = false, insertable=true, updatable=true)
  @CronapiSearchable
  
  private java.lang.Integer cd_status_age;

  /**
  * @generated
  */
  @Column(name = "qt_mudancas_age", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Integer qt_mudancas_age;

  /**
  * @generated
  */
  @Column(name = "cd_corsemana_age", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Integer cd_corsemana_age;

  /**
  * @generated
  */
  @Column(name = "color", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.String color;

  /**
  * @generated
  */
  @Temporal(TemporalType.DATE)
  @Column(name = "startsAt", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.util.Date startsAt;

  /**
  * @generated
  */
  @Temporal(TemporalType.DATE)
  @Column(name = "endsAt", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.util.Date endsAt;

  /**
  * @generated
  */
  @Column(name = "draggable", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Boolean draggable;

  /**
  * @generated
  */
  @Column(name = "resizable", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Boolean resizable;

  /**
  * @generated
  */
  @Column(name = "actions", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.String actions;

  /**
  * @generated
  */
  @Column(name = "responsible", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.Boolean responsible;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_local_Plantao", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Local_Plantao local_Plantao;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_cor_semana", nullable = true, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Cor_Semana corSemana;

  /**
   * Construtor
   * @generated
   */
  public Agenda(){
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
  public Agenda setId(java.lang.String id){
    this.id = id;
    return this;
  }

  /**
   * Obtém horario_Escala
   * return horario_Escala
   * @generated
   */
  
  public Horario_Escala getHorario_Escala(){
    return this.horario_Escala;
  }

  /**
   * Define horario_Escala
   * @param horario_Escala horario_Escala
   * @generated
   */
  public Agenda setHorario_Escala(Horario_Escala horario_Escala){
    this.horario_Escala = horario_Escala;
    return this;
  }

  /**
   * Obtém medico
   * return medico
   * @generated
   */
  
  public Medico getMedico(){
    return this.medico;
  }

  /**
   * Define medico
   * @param medico medico
   * @generated
   */
  public Agenda setMedico(Medico medico){
    this.medico = medico;
    return this;
  }

  /**
   * Obtém dt_agenda_age
   * return dt_agenda_age
   * @generated
   */
  
  public java.util.Date getDt_agenda_age(){
    return this.dt_agenda_age;
  }

  /**
   * Define dt_agenda_age
   * @param dt_agenda_age dt_agenda_age
   * @generated
   */
  public Agenda setDt_agenda_age(java.util.Date dt_agenda_age){
    this.dt_agenda_age = dt_agenda_age;
    return this;
  }

  /**
   * Obtém cd_status_age
   * return cd_status_age
   * @generated
   */
  
  public java.lang.Integer getCd_status_age(){
    return this.cd_status_age;
  }

  /**
   * Define cd_status_age
   * @param cd_status_age cd_status_age
   * @generated
   */
  public Agenda setCd_status_age(java.lang.Integer cd_status_age){
    this.cd_status_age = cd_status_age;
    return this;
  }

  /**
   * Obtém qt_mudancas_age
   * return qt_mudancas_age
   * @generated
   */
  
  public java.lang.Integer getQt_mudancas_age(){
    return this.qt_mudancas_age;
  }

  /**
   * Define qt_mudancas_age
   * @param qt_mudancas_age qt_mudancas_age
   * @generated
   */
  public Agenda setQt_mudancas_age(java.lang.Integer qt_mudancas_age){
    this.qt_mudancas_age = qt_mudancas_age;
    return this;
  }

  /**
   * Obtém cd_corsemana_age
   * return cd_corsemana_age
   * @generated
   */
  
  public java.lang.Integer getCd_corsemana_age(){
    return this.cd_corsemana_age;
  }

  /**
   * Define cd_corsemana_age
   * @param cd_corsemana_age cd_corsemana_age
   * @generated
   */
  public Agenda setCd_corsemana_age(java.lang.Integer cd_corsemana_age){
    this.cd_corsemana_age = cd_corsemana_age;
    return this;
  }

  /**
   * Obtém color
   * return color
   * @generated
   */
  
  public java.lang.String getColor(){
    return this.color;
  }

  /**
   * Define color
   * @param color color
   * @generated
   */
  public Agenda setColor(java.lang.String color){
    this.color = color;
    return this;
  }

  /**
   * Obtém startsAt
   * return startsAt
   * @generated
   */
  
  public java.util.Date getStartsAt(){
    return this.startsAt;
  }

  /**
   * Define startsAt
   * @param startsAt startsAt
   * @generated
   */
  public Agenda setStartsAt(java.util.Date startsAt){
    this.startsAt = startsAt;
    return this;
  }

  /**
   * Obtém endsAt
   * return endsAt
   * @generated
   */
  
  public java.util.Date getEndsAt(){
    return this.endsAt;
  }

  /**
   * Define endsAt
   * @param endsAt endsAt
   * @generated
   */
  public Agenda setEndsAt(java.util.Date endsAt){
    this.endsAt = endsAt;
    return this;
  }

  /**
   * Obtém draggable
   * return draggable
   * @generated
   */
  
  public java.lang.Boolean getDraggable(){
    return this.draggable;
  }

  /**
   * Define draggable
   * @param draggable draggable
   * @generated
   */
  public Agenda setDraggable(java.lang.Boolean draggable){
    this.draggable = draggable;
    return this;
  }

  /**
   * Obtém resizable
   * return resizable
   * @generated
   */
  
  public java.lang.Boolean getResizable(){
    return this.resizable;
  }

  /**
   * Define resizable
   * @param resizable resizable
   * @generated
   */
  public Agenda setResizable(java.lang.Boolean resizable){
    this.resizable = resizable;
    return this;
  }

  /**
   * Obtém actions
   * return actions
   * @generated
   */
  
  public java.lang.String getActions(){
    return this.actions;
  }

  /**
   * Define actions
   * @param actions actions
   * @generated
   */
  public Agenda setActions(java.lang.String actions){
    this.actions = actions;
    return this;
  }

  /**
   * Obtém responsible
   * return responsible
   * @generated
   */
  
  public java.lang.Boolean getResponsible(){
    return this.responsible;
  }

  /**
   * Define responsible
   * @param responsible responsible
   * @generated
   */
  public Agenda setResponsible(java.lang.Boolean responsible){
    this.responsible = responsible;
    return this;
  }

  /**
   * Obtém local_Plantao
   * return local_Plantao
   * @generated
   */
  
  public Local_Plantao getLocal_Plantao(){
    return this.local_Plantao;
  }

  /**
   * Define local_Plantao
   * @param local_Plantao local_Plantao
   * @generated
   */
  public Agenda setLocal_Plantao(Local_Plantao local_Plantao){
    this.local_Plantao = local_Plantao;
    return this;
  }

  /**
   * Obtém corSemana
   * return corSemana
   * @generated
   */
  
  public Cor_Semana getCorSemana(){
    return this.corSemana;
  }

  /**
   * Define corSemana
   * @param corSemana corSemana
   * @generated
   */
  public Agenda setCorSemana(Cor_Semana corSemana){
    this.corSemana = corSemana;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Agenda object = (Agenda)obj;
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
