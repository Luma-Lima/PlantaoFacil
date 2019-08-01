package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.Iterator;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TransferenciaPlantaoi {

public static final int TIMEOUT = 300;

/**
 *
 * @param medico_destino
 * @param medico_origem
 * @param dt_agenda_age
 * @return Var
 */
// TransferenciaPlantaoi
public static Var atualizaPlantao(Var medico_destino, Var medico_origem, Var dt_agenda_age) throws Exception {
 return new Callable<Var>() {

   private Var listaAgendas = Var.VAR_NULL;
   private Var i = Var.VAR_NULL;
   private Var retorno = Var.VAR_NULL;
   private Var erro = Var.VAR_NULL;

   public Var call() throws Exception {
    try {
         listaAgendas = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a.id from Agenda a where a.dt_agenda_age >= :dt_agenda_age AND a.medico.id = :medicoId"),Var.valueOf("dt_agenda_age",dt_agenda_age),Var.valueOf("medicoId",medico_origem));
        if (cronapi.logic.Operations.isNullOrEmpty(listaAgendas).negate().getObjectAsBoolean()) {
            for (Iterator it_i = listaAgendas.iterator(); it_i.hasNext();) {
                i = Var.valueOf(it_i.next());
                cronapi.database.Operations.execute(Var.valueOf("app.entity.Agenda"), Var.valueOf("update Agenda set medico = :medico where id = :id"),Var.valueOf("medico",medico_destino),Var.valueOf("id",i));
            } // end for
            retorno = Var.VAR_TRUE;
        }
     } catch (Exception erro_exception) {
          erro = Var.valueOf(erro_exception);
         retorno = Var.VAR_FALSE;
     }
    return retorno;
   }
 }.call();
}

/**
 *
 * @param medico_id
 * @return Var
 */
// Descreva esta função...
public static Var consultarPlantoesMedico(Var medico_id) throws Exception {
 return new Callable<Var>() {

   private Var erro = Var.VAR_NULL;
   private Var listaPlantoes = Var.VAR_NULL;
   private Var mapaPlantoes = Var.VAR_NULL;
   private Var m = Var.VAR_NULL;
   private Var localPlantao = Var.VAR_NULL;
   private Var horarioEscala = Var.VAR_NULL;
   private Var dataAgendamento = Var.VAR_NULL;
   private Var objetoAgenda = Var.VAR_NULL;

   public Var call() throws Exception {
    listaPlantoes = cronapi.list.Operations.newList();
    mapaPlantoes = cronapi.list.Operations.newList();
    try {
         listaPlantoes = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a.local_Plantao.nm_local_lpl, a.horario_Escala.nm_horario_hre, a.dt_agenda_age from Agenda a where a.medico.id = :medicoId   order by a.dt_agenda_age asc"),Var.valueOf("medicoId",medico_id));
        for (Iterator it_m = listaPlantoes.iterator(); it_m.hasNext();) {
            m = Var.valueOf(it_m.next());
            m = cronapi.json.Operations.toJson(m);
            localPlantao = cronapi.list.Operations.get(m, Var.valueOf(1));
            horarioEscala = cronapi.list.Operations.get(m, Var.valueOf(2));
            dataAgendamento = cronapi.list.Operations.get(m, Var.valueOf(3));
            objetoAgenda = cronapi.map.Operations.createObjectMapWith(Var.valueOf("horarioEscala",horarioEscala) , Var.valueOf("localPlantao",localPlantao) , Var.valueOf("dataAgendamento",dataAgendamento));
            cronapi.list.Operations.addLast(mapaPlantoes,objetoAgenda);
        } // end for
     } catch (Exception erro_exception) {
          erro = Var.valueOf(erro_exception);
         cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), erro);
     }
    return mapaPlantoes;
   }
 }.call();
}

/**
 *
 * @param dataReferencia
 * @return Var
 */
// Descreva esta função...
public static Var validarTransferenciaPlantao(Var dataReferencia) throws Exception {
 return new Callable<Var>() {

   private Var listaPlantoesMedicoOrigem = Var.VAR_NULL;
   private Var contador = Var.VAR_NULL;
   private Var k = Var.VAR_NULL;
   private Var listaPlantoesExistentes = Var.VAR_NULL;
   private Var l = Var.VAR_NULL;

   public Var call() throws Exception {
    listaPlantoesMedicoOrigem = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a.horario_Escala.id, a.local_Plantao.id, a.medico.id from Agenda a"));
    contador = Var.valueOf(0);
    for (Iterator it_k = listaPlantoesMedicoOrigem.iterator(); it_k.hasNext();) {
        k = Var.valueOf(it_k.next());
        k = cronapi.json.Operations.toJson(k);
        listaPlantoesExistentes = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a.id from Agenda a where a.dt_agenda_age = :dt_agenda_age AND a.horario_Escala.id = :horario_EscalaId AND a.medico.id = :medicoId AND a.local_Plantao.id = :local_PlantaoId"),Var.valueOf("dt_agenda_age",dataReferencia),Var.valueOf("horario_EscalaId",cronapi.list.Operations.get(k, Var.valueOf(1))),Var.valueOf("medicoId",cronapi.list.Operations.get(k, Var.valueOf(3))),Var.valueOf("local_PlantaoId",cronapi.list.Operations.get(k, Var.valueOf(2))));
        for (Iterator it_l = listaPlantoesExistentes.iterator(); it_l.hasNext();) {
            l = Var.valueOf(it_l.next());
            if (cronapi.logic.Operations.isNullOrEmpty(listaPlantoesExistentes).negate().getObjectAsBoolean()) {
                contador = cronapi.math.Operations.sum(contador,Var.valueOf(1));
            }
        } // end for
    } // end for
    System.out.println(contador.getObjectAsString());
    return contador;
   }
 }.call();
}

}

