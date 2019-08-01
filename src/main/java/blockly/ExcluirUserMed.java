package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ExcluirUserMed {

public static final int TIMEOUT = 300;

/**
 *
 * @param idUsuario
 * @return Var
 */
// ExcluirUserMed
public static Var ExecutarExcluirUserMed(Var idUsuario) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    System.out.println(Var.valueOf("teste").getObjectAsString());
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Role"), Var.valueOf("delete from Role where user = :user"),Var.valueOf("user",idUsuario));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.Medico"), Var.valueOf("delete from Medico where user = :user"),Var.valueOf("user",idUsuario));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.User"), Var.valueOf("delete from User where id = :id"),Var.valueOf("id",idUsuario));
    return Var.VAR_NULL;
   }
 }.call();
}

}

