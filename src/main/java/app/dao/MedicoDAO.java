package app.dao;

import app.entity.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*; 


/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * Os métodos de create, edit, delete e outros estão abstraídos no JpaRepository
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @generated
 */
@Repository("MedicoDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface MedicoDAO extends JpaRepository<Medico, java.lang.String> {

  /**
   * Obtém a instância de Medico utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Medico entity WHERE entity.id = :id")
  public Medico findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Medico utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Medico entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);


      
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.medico.id = :id AND (:search = :search)")
  public Page<Agenda> findAgendaGeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.String id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.medico.id = :id AND (:dt_agenda_age is null OR entity.dt_agenda_age = :dt_agenda_age) AND (:cd_status_age is null OR entity.cd_status_age = :cd_status_age)")
  public Page<Agenda> findAgendaSpecificSearch(@Param(value="id") java.lang.String id, @Param(value="dt_agenda_age") java.util.Date dt_agenda_age, @Param(value="cd_status_age") java.lang.Integer cd_status_age, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.medico.id = :id")
  public Page<Agenda> findAgenda(@Param(value="id") java.lang.String id, Pageable pageable);
      
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoSolic.id = :id AND (:search = :search)")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancaGeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.String id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoSolic.id = :id AND (:dt_solicitao_mud is null OR entity.dt_solicitao_mud = :dt_solicitao_mud) AND (:cd_status_mud is null OR entity.cd_status_mud = :cd_status_mud)")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancaSpecificSearch(@Param(value="id") java.lang.String id, @Param(value="dt_solicitao_mud") java.util.Date dt_solicitao_mud, @Param(value="cd_status_mud") java.lang.Integer cd_status_mud, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoSolic.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_Mudanca(@Param(value="id") java.lang.String id, Pageable pageable);
      
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoDest.id = :id AND (:search = :search)")
  public Page<Solicitacao_Mudanca> findSolicitacao_Mudanca_2GeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.String id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoDest.id = :id AND (:dt_solicitao_mud is null OR entity.dt_solicitao_mud = :dt_solicitao_mud) AND (:cd_status_mud is null OR entity.cd_status_mud = :cd_status_mud)")
  public Page<Solicitacao_Mudanca> findSolicitacao_Mudanca_2SpecificSearch(@Param(value="id") java.lang.String id, @Param(value="dt_solicitao_mud") java.util.Date dt_solicitao_mud, @Param(value="cd_status_mud") java.lang.Integer cd_status_mud, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoDest.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_Mudanca_2(@Param(value="id") java.lang.String id, Pageable pageable);

  
  /**
   * Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE entity.nr_cpf_med like concat('%',coalesce(:search,''),'%') OR entity.nr_crm_med like concat('%',coalesce(:search,''),'%')")
  public Page<Medico> generalSearch(@Param(value="search") java.lang.String search, Pageable pageable);

  /**
   * Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE (:nr_cpf_med is null OR entity.nr_cpf_med like concat('%',:nr_cpf_med,'%')) AND (:nr_crm_med is null OR entity.nr_crm_med like concat('%',:nr_crm_med,'%')) AND (:qt_hrsupervisao_med is null OR entity.qt_hrsupervisao_med = :qt_hrsupervisao_med) AND (:qt_hrfinanceiro_med is null OR entity.qt_hrfinanceiro_med = :qt_hrfinanceiro_med) AND (:cd_status_med is null OR entity.cd_status_med = :cd_status_med)")
  public Page<Medico> specificSearch(@Param(value="nr_cpf_med") java.lang.String nr_cpf_med, @Param(value="nr_crm_med") java.lang.String nr_crm_med, @Param(value="qt_hrsupervisao_med") java.lang.Integer qt_hrsupervisao_med, @Param(value="qt_hrfinanceiro_med") java.lang.Integer qt_hrfinanceiro_med, @Param(value="cd_status_med") java.lang.Integer cd_status_med, Pageable pageable);
  
  /**
   * Foreign Key user
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE entity.user.id = :id")
  public Page<Medico> findMedicosByUser(@Param(value="id") java.lang.String id, Pageable pageable);

}
