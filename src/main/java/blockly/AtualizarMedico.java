package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class AtualizarMedico {

public static final int TIMEOUT = 300;

/**
 *
 * @param medico_destino
 * @param dt_agenda
 * @param horario_escala
 * @param medico_origem
 * @return Var
 */
// Atualizar_Medico
public static Var Executar(Var medico_destino, Var dt_agenda, Var horario_escala, Var medico_origem) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Agenda"), Var.valueOf("update Agenda set medico = :medico where dt_agenda_age = :dt_agenda_age AND horario_Escala = :horario_Escala AND medico = :medico"),Var.valueOf("medico",medico_destino),Var.valueOf("dt_agenda_age",dt_agenda),Var.valueOf("horario_Escala",horario_escala),Var.valueOf("medico",medico_origem));
    cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("success"), Var.valueOf("OK"));
    return Var.VAR_NULL;
   }
 }.call();
}

}

