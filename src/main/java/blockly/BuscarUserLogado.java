package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class BuscarUserLogado {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// BuscarUserLogado
public static Var ExecutarUserLogado() throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return cronapi.util.Operations.getCurrentUserName();
   }
 }.call();
}

}

