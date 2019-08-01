window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.TransferenciaPlantao = window.blockly.js.blockly.TransferenciaPlantao || {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.TransferenciaPlantao.atualizarListarPlantoes = function() {
 var item, listaPlantoesMedico, mapaAgenda, i, listaPlantoes, retorno;
  this.cronapi.screen.createScopeVariable('listaPlantoesMedico', []);
  listaPlantoesMedico = [];
  this.cronapi.util.callServerBlocklyAsynchronous('blockly.TransferenciaPlantaoi:consultarPlantoesMedico', function(sender_listaPlantoes) {
      listaPlantoes = sender_listaPlantoes;
    for (var i_index in listaPlantoes) {
      i = listaPlantoes[i_index];
      mapaAgenda = this.cronapi.object.newObject();
      this.cronapi.object.setProperty(mapaAgenda, 'localPlantao', this.cronapi.object.getProperty(i, 'localPlantao'));
      this.cronapi.object.setProperty(mapaAgenda, 'dataAgendamento', this.cronapi.myfunctions.converterMillisegundosParaData(this.cronapi.object.getProperty(i, 'dataAgendamento')));
      this.cronapi.object.setProperty(mapaAgenda, 'horarioEscala', this.cronapi.object.getProperty(i, 'horarioEscala'));
      listaPlantoesMedico.push(mapaAgenda);
    }
    this.cronapi.screen.createScopeVariable('listaPlantoesMedico', listaPlantoesMedico);
  }.bind(this), this.cronapi.screen.getValueOfField("vars.medicoOrigem"));
}

/**
 * TransferenciaPlantao
 */
window.blockly.js.blockly.TransferenciaPlantao.validaCampos = function() {
 var item, listaPlantoesMedico, mapaAgenda, i, listaPlantoes, retorno;
  if (this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("vars.medicoOrigem"))) {
    this.cronapi.screen.notify('warning','Selecione o médico de origem');
  } else if (this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("vars.datasAgendamento"))) {
    this.cronapi.screen.notify('warning','Selecione a data de origem');
  } else if (this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("vars.medicoDestino"))) {
    this.cronapi.screen.notify('warning','Selecione o médico de destino');
  } else {
    this.cronapi.util.callServerBlocklyAsynchronous('blockly.TransferenciaPlantaoi:atualizaPlantao', function(sender_retorno) {
        retorno = sender_retorno;
      if (retorno == true) {
        this.cronapi.screen.notify('success','Transferência realizada com sucesso.');
        this.cronapi.screen.changeView("#/home/admin/agenda",[  ]);
      }
    }.bind(this), this.cronapi.screen.getValueOfField("vars.medicoDestino"), this.cronapi.screen.getValueOfField("vars.medicoOrigem"), this.cronapi.dateTime.formatDateTime(this.cronapi.dateTime.incHour(this.cronapi.screen.getValueOfField("vars.datasAgendamento"), 3), 'DD/MM/YYYY'));
  }
}
