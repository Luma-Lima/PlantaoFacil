package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.Iterator;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class SolicitacaoMudanca {

public static final int TIMEOUT = 300;

/**
 *
 * @param idSolicitacaoMudanca
 * @return Var
 */
// SolicitacaoMudancaAoConfirmar
public static Var confirmar(Var idSolicitacaoMudanca) throws Exception {
 return new Callable<Var>() {

   private Var solicitacaoMudanca = Var.VAR_NULL;
   private Var retorno = Var.VAR_NULL;

   public Var call() throws Exception {
    solicitacaoMudanca = cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s from Solicitacao_Mudanca s where s.id = :id"),Var.valueOf("id",idSolicitacaoMudanca));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Agenda"), Var.valueOf("update Agenda set medico = :medico where id = :id"),Var.valueOf("medico",cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("medicoDest"))),Var.valueOf("id",cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("agenda.id"))));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Solicitacao_Mudanca"), Var.valueOf("update Solicitacao_Mudanca set cd_status_mud = :cd_status_mud where id = :id"),Var.valueOf("cd_status_mud",Var.valueOf(1)),Var.valueOf("id",idSolicitacaoMudanca));
    blockly.SolicitacaoMudanca.enviarNotificacao(solicitacaoMudanca, Var.VAR_TRUE);
    retorno = Var.valueOf("OK");
    return retorno;
   }
 }.call();
}

/**
 *
 * @return Var
 */
// SolicitacaoMudanca
public static Var existePedido() throws Exception {
 return new Callable<Var>() {

   private Var idSolicitacaoMudanca = Var.VAR_NULL;
   private Var existeMudanca = Var.VAR_NULL;

   public Var call() throws Exception {
    idSolicitacaoMudanca = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s.id from Solicitacao_Mudanca s where s.medicoDest.user.login = :medicoDestUserLogin AND s.cd_status_mud = :cd_status_mud"),Var.valueOf("medicoDestUserLogin",cronapi.util.Operations.getCurrentUserName()),Var.valueOf("cd_status_mud",Var.valueOf(0)))));
    existeMudanca = Var.VAR_FALSE;
    if (Var.valueOf(Var.valueOf(!idSolicitacaoMudanca.equals(Var.VAR_NULL)).getObjectAsBoolean() && Var.valueOf(!cronapi.list.Operations.get(idSolicitacaoMudanca, Var.valueOf(1)).equals(Var.VAR_NULL)).getObjectAsBoolean()).getObjectAsBoolean()) {
        existeMudanca = Var.VAR_TRUE;
    }
    return existeMudanca;
   }
 }.call();
}

/**
 *
 * @param idSolicitacaoMudanca
 * @return Var
 */
// SolicitacaoMudancaAoConfirmar
public static Var recusar(Var idSolicitacaoMudanca) throws Exception {
 return new Callable<Var>() {

   private Var solicitacaoMudanca = Var.VAR_NULL;
   private Var retorno = Var.VAR_NULL;

   public Var call() throws Exception {
    solicitacaoMudanca = cronapi.database.Operations.query(Var.valueOf("app.entity.Solicitacao_Mudanca"),Var.valueOf("select s from Solicitacao_Mudanca s where s.id = :id"),Var.valueOf("id",idSolicitacaoMudanca));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Solicitacao_Mudanca"), Var.valueOf("update Solicitacao_Mudanca set cd_status_mud = :cd_status_mud where id = :id"),Var.valueOf("cd_status_mud",Var.valueOf(2)),Var.valueOf("id",idSolicitacaoMudanca));
    blockly.SolicitacaoMudanca.enviarNotificacao(solicitacaoMudanca, Var.VAR_FALSE);
    retorno = Var.valueOf("OK");
    return retorno;
   }
 }.call();
}

/**
 *
 * @param solicitacaoMudanca
 * @param aceito
 */
// Descreva esta função...
public static void enviarNotificacao(Var solicitacaoMudanca, Var aceito) throws Exception {
  new Callable<Var>() {

   private Var destinatarios = Var.VAR_NULL;
   private Var texto = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    destinatarios = cronapi.database.Operations.query(Var.valueOf("app.entity.Device"),Var.valueOf("select d from Device d where d.user.id = :userId"),Var.valueOf("userId",cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("medicoSolic.user.id"))));
    texto = Var.valueOf("Confirmacao Mudanca Plantao");
    if (Var.valueOf(aceito.equals(Var.VAR_FALSE)).getObjectAsBoolean()) {
        texto = Var.valueOf("Medico recusou mudanca de plantao.");
    }
    for (Iterator it_item = destinatarios.iterator(); it_item.hasNext();) {
        item = Var.valueOf(it_item.next());
        cronapi.pushnotification.Operations.sendNotification(blockly.Notificacao.obterChaveServidor(), cronapi.object.Operations.getObjectField(item, Var.valueOf("token")), texto, Var.valueOf(Var.valueOf("Medico: ").toString() + cronapi.object.Operations.getObjectField(solicitacaoMudanca, Var.valueOf("medicoDest.user.name")).toString()), Var.VAR_NULL);
    } // end for
    cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("success"), Var.valueOf("Mensagem Enviada."));
   return Var.VAR_NULL;
   }
 }.call();
}

}

