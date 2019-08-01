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
@Repository("UserDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface UserDAO extends JpaRepository<User, java.lang.String> {

  /**
   * Obtém a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM User entity WHERE entity.id = :id")
  public User findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM User entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);



  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Role entity WHERE entity.user.id = :id")
  public Page<Role> findRole(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Device entity WHERE entity.user.id = :id")
  public Page<Device> findDevice(@Param(value="id") java.lang.String id, Pageable pageable);
    
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE entity.user.id = :id AND (entity.nr_cpf_med like concat('%',coalesce(:search,''),'%') OR entity.nr_crm_med like concat('%',coalesce(:search,''),'%'))")
  public Page<Medico> findMedicoGeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.String id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE entity.user.id = :id AND (:nr_cpf_med is null OR entity.nr_cpf_med like concat('%',:nr_cpf_med,'%')) AND (:nr_crm_med is null OR entity.nr_crm_med like concat('%',:nr_crm_med,'%')) AND (:qt_hrsupervisao_med is null OR entity.qt_hrsupervisao_med = :qt_hrsupervisao_med) AND (:qt_hrfinanceiro_med is null OR entity.qt_hrfinanceiro_med = :qt_hrfinanceiro_med) AND (:cd_status_med is null OR entity.cd_status_med = :cd_status_med)")
  public Page<Medico> findMedicoSpecificSearch(@Param(value="id") java.lang.String id, @Param(value="nr_cpf_med") java.lang.String nr_cpf_med, @Param(value="nr_crm_med") java.lang.String nr_crm_med, @Param(value="qt_hrsupervisao_med") java.lang.Integer qt_hrsupervisao_med, @Param(value="qt_hrfinanceiro_med") java.lang.Integer qt_hrfinanceiro_med, @Param(value="cd_status_med") java.lang.Integer cd_status_med, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Medico entity WHERE entity.user.id = :id")
  public Page<Medico> findMedico(@Param(value="id") java.lang.String id, Pageable pageable);

}
