window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.SolicitacaoMudanca = window.blockly.js.blockly.SolicitacaoMudanca || {};

/**
 * SolicitacaoMudanca
 */
window.blockly.js.blockly.SolicitacaoMudanca.Executar = function() {
 var item, valor, idSolicitacaoMudanca, aceito;
  this.cronapi.util.scheduleExecution(function() {
     this.cronapi.util.callServerBlocklyAsynchronous('blockly.SolicitacaoMudanca:existePedido', function(sender_valor) {
        valor = sender_valor;
      this.cronapi.screen.changeValueOfField('vars.existeSolicitacao', valor);
    }.bind(this));
  }.bind(this), 0, 15, 'seconds');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.SolicitacaoMudanca.confirmar = function(idSolicitacaoMudanca) {
 var item, valor, aceito;
  aceito = this.cronapi.screen.getValueOfField('vars.aceito');
  if (aceito == 'S') {
    this.cronapi.util.callServerBlocklyAsynchronous('blockly.SolicitacaoMudanca:confirmar', function(sender_item) {
        item = sender_item;
      this.cronapi.util.executeJavascriptNoReturn('Solicitacao_Mudanca.cancel();');
      this.cronapi.util.executeJavascriptNoReturn('Solicitacao_Mudanca.search();');
    }.bind(this), idSolicitacaoMudanca);
  } else if (aceito == 'N') {
    this.cronapi.util.callServerBlocklyAsynchronous('blockly.SolicitacaoMudanca:recusar', function(sender_item) {
        item = sender_item;
      this.cronapi.util.executeJavascriptNoReturn('Solicitacao_Mudanca.cancel();');
      this.cronapi.util.executeJavascriptNoReturn('Solicitacao_Mudanca.search();');
    }.bind(this), idSolicitacaoMudanca);
  }
  this.cronapi.screen.changeValueOfField('vars.aceito', '');
}
