package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Agenda {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// Agenda
public static Var obterAnos() throws Exception {
 return new Callable<Var>() {

   private Var lista = Var.VAR_NULL;

   public Var call() throws Exception {
    lista = cronapi.list.Operations.newList();
    cronapi.list.Operations.addLast(lista,cronapi.object.Operations.newObject(Var.valueOf("app.dto.AnoAgenda"),Var.valueOf("id",Var.valueOf(1)),Var.valueOf("value",cronapi.dateTime.Operations.getYear(cronapi.dateTime.Operations.getNow()))));
    cronapi.list.Operations.addLast(lista,cronapi.object.Operations.newObject(Var.valueOf("app.dto.AnoAgenda"),Var.valueOf("id",Var.valueOf(2)),Var.valueOf("value",cronapi.dateTime.Operations.getYear(cronapi.dateTime.Operations.incYear(cronapi.dateTime.Operations.getNow(), Var.valueOf(1))))));
    System.out.println(lista.getObjectAsString());
    return lista;
   }
 }.call();
}

/**
 *
 * @param idMes
 * @param mesParam
 * @return Var
 */
// Descreva esta função...
public static Var adicionarMes(Var idMes, Var mesParam) throws Exception {
 return new Callable<Var>() {

   private Var listaMesesAgend = Var.VAR_NULL;
   private Var objMesAdd = Var.VAR_NULL;

   public Var call() throws Exception {
    listaMesesAgend = cronapi.list.Operations.newList();
    objMesAdd = cronapi.object.Operations.newObject(Var.valueOf("app.dto.MesAgenda"),Var.valueOf("id",idMes),Var.valueOf("value",mesParam));
    cronapi.list.Operations.addLast(listaMesesAgend,objMesAdd);
    return listaMesesAgend;
   }
 }.call();
}

/**
 *
 * @param param
 * @return Var
 */
// Descreva esta função...
public static Var consultarAgendamento(Var param) throws Exception {
 return new Callable<Var>() {

   private Var item = Var.VAR_NULL;
   private Var result = Var.VAR_NULL;
   private Var dataResultJson = Var.VAR_NULL;

   public Var call() throws Exception {
    try {
         result = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a from Agenda a where a.horario_Escala = :horario_Escala AND a.local_Plantao = :local_Plantao AND a.dt_agenda_age = :dt_agenda_age"),Var.valueOf("horario_Escala",cronapi.json.Operations.getJsonOrMapField(param, Var.valueOf("horario_Escala"))),Var.valueOf("local_Plantao",cronapi.json.Operations.getJsonOrMapField(param, Var.valueOf("local_Plantao"))),Var.valueOf("dt_agenda_age",cronapi.json.Operations.getJsonOrMapField(param, Var.valueOf("dt_agenda_age"))));
        dataResultJson = cronapi.json.Operations.createObjectJson();
        if (Var.valueOf(cronapi.list.Operations.size(result).equals(Var.valueOf(0))).getObjectAsBoolean()) {
            cronapi.json.Operations.setJsonOrMapField(dataResultJson, Var.valueOf("existeRegistro"), Var.VAR_FALSE);
        } else {
            cronapi.json.Operations.setJsonOrMapField(dataResultJson, Var.valueOf("existeRegistro"), Var.VAR_TRUE);
        }
        cronapi.json.Operations.setJsonOrMapField(dataResultJson, Var.valueOf("dt_agenda_age"), cronapi.json.Operations.getJsonOrMapField(param, Var.valueOf("dt_agenda_age")));
     } catch (Exception item_exception) {
          item = Var.valueOf(item_exception);
         cronapi.util.Operations.throwException(item);
     }
    return dataResultJson;
   }
 }.call();
}

/**
 *
 * @param param
 * @return Var
 */
// Descreva esta função...
public static Var inserirAgendamento(Var param) throws Exception {
 return new Callable<Var>() {

   private Var retorno = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    try {
         cronapi.database.Operations.insert(Var.valueOf("app.entity.Agenda"),param);
        retorno = Var.VAR_TRUE;
     } catch (Exception item_exception) {
          item = Var.valueOf(item_exception);
         cronapi.util.Operations.throwException(item);
     }
    return retorno;
   }
 }.call();
}

/**
 *
 * @param anoParam
 * @return Var
 */
// Descreva esta função...
public static Var obterMeses(Var anoParam) throws Exception {
 return new Callable<Var>() {

   private Var mesParam = Var.VAR_NULL;
   private Var listaMeses = Var.VAR_NULL;

   public Var call() throws Exception {
    listaMeses = cronapi.list.Operations.newList();
    if (Var.valueOf(anoParam.equals(cronapi.dateTime.Operations.getYear(cronapi.dateTime.Operations.getNow()))).getObjectAsBoolean()) {
        mesParam = cronapi.dateTime.Operations.getMonth(cronapi.dateTime.Operations.getNow());
        while (Var.valueOf(mesParam.compareTo(Var.valueOf(12)) <= 0).getObjectAsBoolean()) {
            cronapi.list.Operations.addLast(listaMeses,cronapi.object.Operations.newObject(Var.valueOf("app.dto.MesAgenda"),Var.valueOf("id",mesParam),Var.valueOf("value",mesParam)));
            mesParam = cronapi.math.Operations.sum(mesParam,Var.valueOf(1));
        } // end while
    } else {
        mesParam = Var.valueOf(1);
        while (Var.valueOf(mesParam.compareTo(Var.valueOf(12)) <= 0).getObjectAsBoolean()) {
            cronapi.list.Operations.addLast(listaMeses,cronapi.object.Operations.newObject(Var.valueOf("app.dto.MesAgenda"),Var.valueOf("id",mesParam),Var.valueOf("value",mesParam)));
            mesParam = cronapi.math.Operations.sum(mesParam,Var.valueOf(1));
        } // end while
    }
    return listaMeses;
   }
 }.call();
}

/**
 *
 * @return Var
 */
// Descreva esta função...
public static Var obterSemanas() throws Exception {
 return new Callable<Var>() {

   private Var listSemana = Var.VAR_NULL;

   public Var call() throws Exception {
    listSemana = cronapi.list.Operations.newList(cronapi.object.Operations.newObject(Var.valueOf("app.dto.CorSemana"),Var.valueOf("id",Var.valueOf("1")),Var.valueOf("descricao",Var.valueOf("vermelho"))), cronapi.object.Operations.newObject(Var.valueOf("app.dto.CorSemana"),Var.valueOf("id",Var.valueOf("2")),Var.valueOf("descricao",Var.valueOf("verde"))), cronapi.object.Operations.newObject(Var.valueOf("app.dto.CorSemana"),Var.valueOf("id",Var.valueOf("3")),Var.valueOf("descricao",Var.valueOf("cinza"))), cronapi.object.Operations.newObject(Var.valueOf("app.dto.CorSemana"),Var.valueOf("id",Var.valueOf("4")),Var.valueOf("descricao",Var.valueOf("azul"))), cronapi.object.Operations.newObject(Var.valueOf("app.dto.CorSemana"),Var.valueOf("id",Var.valueOf("5")),Var.valueOf("descricao",Var.valueOf("vermelho2"))));
    return listSemana;
   }
 }.call();
}

}

