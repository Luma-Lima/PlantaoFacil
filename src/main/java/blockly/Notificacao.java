package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.Iterator;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Notificacao {

public static final int TIMEOUT = 300;

/**
 *
 * @param agendaId
 * @param usuarioDestinoId
 * @return Var
 */
// Notificacao
public static Var solicitarMudancaAgenda(Var agendaId, Var usuarioDestinoId) throws Exception {
 return new Callable<Var>() {

   private Var agenda = Var.VAR_NULL;
   private Var medicoDestino = Var.VAR_NULL;
   private Var loginAgenda = Var.VAR_NULL;
   private Var medicoSolicitante = Var.VAR_NULL;
   private Var agendaMedicoDest = Var.VAR_NULL;
   private Var solicitacaoMudanca = Var.VAR_NULL;

   public Var call() throws Exception {
    if (Var.valueOf(cronapi.logic.Operations.isNullOrEmpty(agendaId).negate().getObjectAsBoolean() && cronapi.logic.Operations.isNullOrEmpty(usuarioDestinoId).negate().getObjectAsBoolean()).getObjectAsBoolean()) {
        agenda = cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a from Agenda a where a.id = :id"),Var.valueOf("id",agendaId));
        medicoDestino = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Medico"),Var.valueOf("select m from Medico m where m.user.id = :userId"),Var.valueOf("userId",usuarioDestinoId))));
        if (Var.valueOf(Var.valueOf(Var.valueOf(!agenda.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(agenda, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean() && (Var.valueOf(Var.valueOf(!medicoDestino.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(medicoDestino, Var.valueOf("user")).equals(Var.VAR_NULL)).getObjectAsBoolean())).getObjectAsBoolean()).getObjectAsBoolean()) {
            loginAgenda = cronapi.object.Operations.getObjectField(agenda, Var.valueOf("medico.user.login"));
            medicoSolicitante = cronapi.object.Operations.getObjectField(agenda, Var.valueOf("medico"));
            if (Var.valueOf(!loginAgenda.equals(cronapi.util.Operations.getCurrentUserName())).getObjectAsBoolean()) {
                cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Operação não permitida! O plantão não pertence ao usuário corrente."));
            } else if (Var.valueOf(cronapi.object.Operations.getObjectField(medicoSolicitante, Var.valueOf("id")).equals(cronapi.object.Operations.getObjectField(medicoDestino, Var.valueOf("id")))).getObjectAsBoolean()) {
                cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Não é permitido realizar solicitação de mudança de plantão! Médico Solicitante igual ao médico Solicitado."));
            } else {
                agendaMedicoDest = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Agenda"),Var.valueOf("select a from Agenda a where a.medico.id = :medicoId AND a.dt_agenda_age = :dt_agenda_age AND a.horario_Escala.id = :horario_EscalaId"),Var.valueOf("medicoId",cronapi.object.Operations.getObjectField(medicoDestino, Var.valueOf("id"))),Var.valueOf("dt_agenda_age",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("dt_agenda_age"))),Var.valueOf("horario_EscalaId",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("horario_Escala.id"))))));
                if (Var.valueOf(Var.valueOf(!agendaMedicoDest.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(agendaMedicoDest, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                    cronapi.util.Operations.throwException(cronapi.util.Operations.createException(Var.valueOf("Médico selecionado já possui um plantão para este dia.")));
                }
                solicitacaoMudanca = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s from Solicitacao_Mudanca s where s.agenda.id = :agendaId AND s.cd_status_mud = :cd_status_mud"),Var.valueOf("agendaId",agendaId),Var.valueOf("cd_status_mud",Var.valueOf(0)))));
                if (Var.valueOf(Var.valueOf(!solicitacaoMudanca.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                    cronapi.util.Operations.throwException(cronapi.util.Operations.createException(Var.valueOf(Var.valueOf("Já foi enviada uma solicitação de mudança para este plantão! ").toString() + Var.valueOf(" Aguardando resposta de: ").toString() + cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("medicoDest.user.name")).toString())));
                }
                solicitacaoMudanca = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s from Solicitacao_Mudanca s where s.cd_status_mud = :cd_status_mud AND s.agenda.dt_agenda_age = :dt_agenda_age AND s.agenda.horario_Escala.id = :horario_EscalaId AND s.medicoSolic.id = :medicoSolicId"),Var.valueOf("cd_status_mud",Var.valueOf(0)),Var.valueOf("dt_agenda_age",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("dt_agenda_age"))),Var.valueOf("horario_EscalaId",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("horario_Escala.id"))),Var.valueOf("medicoSolicId",cronapi.object.Operations.getObjectField(medicoSolicitante, Var.valueOf("id"))))));
                if (Var.valueOf(Var.valueOf(!solicitacaoMudanca.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                    cronapi.util.Operations.throwException(cronapi.util.Operations.createException(Var.valueOf("Não foi possível realizar a solicitação de mudança de plantão! Existe uma solicitação para o usuário atual.")));
                }
                solicitacaoMudanca = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s from Solicitacao_Mudanca s where s.cd_status_mud = :cd_status_mud AND s.agenda.dt_agenda_age = :dt_agenda_age AND s.agenda.horario_Escala.id = :horario_EscalaId AND s.medicoDest.id = :medicoDestId"),Var.valueOf("cd_status_mud",Var.valueOf(0)),Var.valueOf("dt_agenda_age",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("dt_agenda_age"))),Var.valueOf("horario_EscalaId",cronapi.object.Operations.getObjectField(agenda, Var.valueOf("horario_Escala.id"))),Var.valueOf("medicoDestId",cronapi.object.Operations.getObjectField(medicoDestino, Var.valueOf("id"))))));
                if (Var.valueOf(Var.valueOf(!solicitacaoMudanca.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                    cronapi.util.Operations.throwException(cronapi.util.Operations.createException(Var.valueOf("Não foi possível realizar a solicitação de mudança de plantão! O médico selecionado indisponível.")));
                }
                blockly.Notificacao.enviarNotificacao(agenda, medicoSolicitante, medicoDestino);
            }
        } else {
            cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Não foi possível realizar a solicitação de mudança de plantão! Agenda ou médico não disponível."));
        }
    } else {
        cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Não foi possível realizar a solicitação de mudança de plantão! Agenda não disponível."));
    }
    return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param agendaAtual
 * @param medicoSolicitante
 * @param medicoDestino
 */
// Descreva esta função...
public static void enviarNotificacao(Var agendaAtual, Var medicoSolicitante, Var medicoDestino) throws Exception {
  new Callable<Var>() {

   private Var dados = Var.VAR_NULL;
   private Var solicitacaoMudanca = Var.VAR_NULL;
   private Var idSolicitacaoMudanca = Var.VAR_NULL;
   private Var destinatarios = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    idSolicitacaoMudanca = cronapi.util.Operations.generateUUID();
    solicitacaoMudanca = cronapi.object.Operations.newObject(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("id",idSolicitacaoMudanca),Var.valueOf("horario_Escala",cronapi.object.Operations.getObjectField(agendaAtual, Var.valueOf("horario_Escala"))),Var.valueOf("dt_solicitao_mud",cronapi.dateTime.Operations.getNowNoHour()),Var.valueOf("cd_status_mud",Var.valueOf(0)),Var.valueOf("agenda",cronapi.object.Operations.newObject(Var.valueOf("app.entity.Agenda"),Var.valueOf("id",cronapi.object.Operations.getObjectField(agendaAtual, Var.valueOf("id"))))),Var.valueOf("medicoSolic",medicoSolicitante),Var.valueOf("medicoDest",medicoDestino));
    cronapi.database.Operations.insert(Var.valueOf("app.entity.Solicitacao_Mudanca"),solicitacaoMudanca);
    dados = cronapi.json.Operations.createObjectJson();
    cronapi.json.Operations.setJsonOrMapField(dados, Var.valueOf("idSolicitacaoMudanca"), idSolicitacaoMudanca);
    destinatarios = cronapi.database.Operations.query(Var.valueOf("app.entity.Device"),Var.valueOf("select d from Device d where d.user.id = :userId"),Var.valueOf("userId",cronapi.object.Operations.getObjectField(medicoDestino, Var.valueOf("user.id"))));
    for (Iterator it_item = destinatarios.iterator(); it_item.hasNext();) {
        item = Var.valueOf(it_item.next());
        cronapi.pushnotification.Operations.sendNotification(blockly.Notificacao.obterChaveServidor(), cronapi.object.Operations.getObjectField(item, Var.valueOf("token")), Var.valueOf("Solicitacao Mudanca de Plantao"), Var.valueOf(Var.valueOf("Solicitante: ").toString() + cronapi.object.Operations.getObjectField(medicoSolicitante, Var.valueOf("user.name")).toString()), dados);
    } // end for
    cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("success"), Var.valueOf("Solicitação de Mudança Enviada."));
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param dados
 */
// Descreva esta função...
public static void gravarDispositivo(Var dados) throws Exception {
  new Callable<Var>() {

   private Var usuario = Var.VAR_NULL;
   private Var uuid = Var.VAR_NULL;
   private Var token = Var.VAR_NULL;
   private Var dispositivo = Var.VAR_NULL;

   public Var call() throws Exception {
    usuario = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select u from User u where u.login = :login"),Var.valueOf("login",cronapi.util.Operations.getCurrentUserName()));
    uuid = cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("uuid"));
    if (cronapi.logic.Operations.isNullOrEmpty(uuid).getObjectAsBoolean()) {
        uuid = Var.valueOf(Var.valueOf("crn-").toString() + cronapi.object.Operations.getObjectField(usuario, Var.valueOf("id")).toString());
    }
    token = cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("token"));
    try {
         if (cronapi.logic.Operations.isNullOrEmpty(uuid).negate().getObjectAsBoolean()) {
            dispositivo = cronapi.database.Operations.query(Var.valueOf("app.entity.Device"),Var.valueOf("select d from Device d where d.id = :id"),Var.valueOf("id",uuid));
            if (Var.valueOf(Var.valueOf(!dispositivo.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(dispositivo, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                if (Var.valueOf(Var.valueOf(!usuario.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.object.Operations.getObjectField(usuario, Var.valueOf("id")).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
                    cronapi.object.Operations.setObjectField(dispositivo, Var.valueOf("user"), cronapi.object.Operations.newObject(Var.valueOf("app.entity.User"),Var.valueOf("id",cronapi.object.Operations.getObjectField(usuario, Var.valueOf("id")))));
                    cronapi.object.Operations.setObjectField(dispositivo, Var.valueOf("token"), token);
                    cronapi.database.Operations.update(Var.valueOf("app.entity.Device"),dispositivo);
                }
            } else {
                dispositivo = cronapi.object.Operations.newObject(Var.valueOf("app.entity.Device"),Var.valueOf("id",uuid),Var.valueOf("token",token),Var.valueOf("platform",cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("platform"))),Var.valueOf("model",cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("model"))),Var.valueOf("platformVersion",cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("platformVersion"))),Var.valueOf("appName",cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("appName"))),Var.valueOf("appVersion",cronapi.json.Operations.getJsonOrMapField(dados, Var.valueOf("appVersion"))),Var.valueOf("user",cronapi.object.Operations.newObject(Var.valueOf("app.entity.User"),Var.valueOf("id",cronapi.object.Operations.getObjectField(usuario, Var.valueOf("id"))))));
                cronapi.database.Operations.insert(Var.valueOf("app.entity.Device"),dispositivo);
            }
        }
     } catch (Exception ex1_exception) {

     } finally {
     }
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @return Var
 */
// Descreva esta função...
public static Var obterChaveServidor() throws Exception {
 return new Callable<Var>() {

   private Var usuario = Var.VAR_NULL;
   private Var uuid = Var.VAR_NULL;
   private Var dados = Var.VAR_NULL;
   private Var token = Var.VAR_NULL;
   private Var dispositivo = Var.VAR_NULL;
   private Var agendaId = Var.VAR_NULL;
   private Var usuarioDestinoId = Var.VAR_NULL;
   private Var agenda = Var.VAR_NULL;
   private Var medicoDestino = Var.VAR_NULL;
   private Var loginAgenda = Var.VAR_NULL;
   private Var medicoSolicitante = Var.VAR_NULL;
   private Var agendaMedicoDest = Var.VAR_NULL;
   private Var solicitacaoMudanca = Var.VAR_NULL;

   public Var call() throws Exception {
    return Var.valueOf("AAAAN0lcF-M:APA91bHxfOzk-mlXM0lUzkNnO1X1eJ1G9-P8EmgHdxi8dIQpUA-QxHT6uQqdRbI7Yogu2hJIzPqnIulAoyrO3ORrl3apkIQv-QxMvBEDL2jXpfdThuQFYr8eH-IZdWvd3J-xW6H7Fvho");
   }
 }.call();
}

}

