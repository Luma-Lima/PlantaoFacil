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
@Repository("Solicitacao_MudancaDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface Solicitacao_MudancaDAO extends JpaRepository<Solicitacao_Mudanca, java.lang.String> {

  /**
   * Obtém a instância de Solicitacao_Mudanca utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.id = :id")
  public Solicitacao_Mudanca findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Solicitacao_Mudanca utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Solicitacao_Mudanca entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);



    
  /**
   * Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE :search = :search")
  public Page<Solicitacao_Mudanca> generalSearch(@Param(value="search") java.lang.String search, Pageable pageable);

  /**
   * Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE (:dt_solicitao_mud is null OR entity.dt_solicitao_mud = :dt_solicitao_mud) AND (:cd_status_mud is null OR entity.cd_status_mud = :cd_status_mud)")
  public Page<Solicitacao_Mudanca> specificSearch(@Param(value="dt_solicitao_mud") java.util.Date dt_solicitao_mud, @Param(value="cd_status_mud") java.lang.Integer cd_status_mud, Pageable pageable);
  
  /**
   * Foreign Key horario_Escala
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.horario_Escala.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancasByHorario_Escala(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * Foreign Key agenda
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.agenda.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancasByAgenda(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * Foreign Key medicoSolic
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoSolic.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancasByMedicoSolic(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * Foreign Key medicoDest
   * @generated
   */
  @Query("SELECT entity FROM Solicitacao_Mudanca entity WHERE entity.medicoDest.id = :id")
  public Page<Solicitacao_Mudanca> findSolicitacao_MudancasByMedicoDest(@Param(value="id") java.lang.String id, Pageable pageable);

}
