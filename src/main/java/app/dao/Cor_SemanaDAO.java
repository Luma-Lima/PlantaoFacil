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
@Repository("Cor_SemanaDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface Cor_SemanaDAO extends JpaRepository<Cor_Semana, java.lang.Integer> {

  /**
   * Obtém a instância de Cor_Semana utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Cor_Semana entity WHERE entity.id = :id")
  public Cor_Semana findOne(@Param(value="id") java.lang.Integer id);

  /**
   * Remove a instância de Cor_Semana utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Cor_Semana entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.Integer id);


      
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.corSemana.id = :id AND (:search = :search)")
  public Page<Agenda> findAgendaGeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.Integer id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.corSemana.id = :id AND (:dt_agenda_age is null OR entity.dt_agenda_age = :dt_agenda_age) AND (:cd_status_age is null OR entity.cd_status_age = :cd_status_age)")
  public Page<Agenda> findAgendaSpecificSearch(@Param(value="id") java.lang.Integer id, @Param(value="dt_agenda_age") java.util.Date dt_agenda_age, @Param(value="cd_status_age") java.lang.Integer cd_status_age, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Agenda entity WHERE entity.corSemana.id = :id")
  public Page<Agenda> findAgenda(@Param(value="id") java.lang.Integer id, Pageable pageable);

}
