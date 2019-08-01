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
@Repository("HospitaisDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface HospitaisDAO extends JpaRepository<Hospitais, java.lang.String> {

  /**
   * Obtém a instância de Hospitais utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Hospitais entity WHERE entity.id = :id")
  public Hospitais findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Hospitais utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Hospitais entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);



  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Local_Plantao entity WHERE entity.hospitais.id = :id")
  public Page<Local_Plantao> findLocal_Plantao(@Param(value="id") java.lang.String id, Pageable pageable);

}
