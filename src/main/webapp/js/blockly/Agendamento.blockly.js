window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.Agendamento = window.blockly.js.blockly.Agendamento || {};

/**
 * Agendamento
 */
window.blockly.js.blockly.Agendamento.adicionarMes = function() {
 var item, anoMes, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, maiorData, menorData, mes, numeroSemana, objAgendamento, param, retorno;
  listaMesAdd = this.cronapi.screen.getScopeVariable('listaMeses');
  if (this.cronapi.logic.isNullOrEmpty(listaMesAdd)) {
    listaMesAdd = [];
  }
  item = this.cronapi.object.newObject({name: 'id', value: this.cronapi.screen.getValueOfField("ObterMeses.active.id")}, {name: 'value', value: this.cronapi.screen.getValueOfField("ObterMeses.active.value")}, {name: 'anoValue', value: this.cronapi.screen.getValueOfField("ComboAno.active.value")});
  existente = false;
  for (var j_index in listaMesAdd) {
    j = listaMesAdd[j_index];
    if (String(this.cronapi.object.getProperty(j, 'value')) + String(this.cronapi.object.getProperty(j, 'anoValue')) == String(this.cronapi.object.getProperty(item, 'value')) + String(this.cronapi.object.getProperty(item, 'anoValue'))) {
      existente = true;
    }
  }
  if (!existente) {
    listaMesAdd.push(item);
  }
  this.cronapi.screen.createScopeVariable('listaMeses', listaMesAdd);
  return listaMesAdd;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Agendamento.removerDiaAgendamento = function(param) {
 var item, anoMes, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, maiorData, menorData, mes, numeroSemana, objAgendamento, retorno;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  count = 1;
  item = listaDatas.indexOf(param) + 1;
  if (item != 0) {
    listaDatas.splice((item - 1), 1);
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Agendamento.adicionarDiaAgendamento = function() {
 var item, anoMes, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, maiorData, menorData, mes, numeroSemana, objAgendamento, param, retorno;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  if (this.cronapi.logic.isNullOrEmpty(listaDatas)) {
    listaDatas = [];
  }
  existente = listaDatas.indexOf(this.cronapi.dateTime.formatDateTime(this.cronapi.dateTime.incHour(this.cronapi.screen.getValueOfField("vars.dataAgendamento"), 3), 'DD/MM/YYYY')) + 1;
  isValid = this.blockly.js.blockly.Agendamento.validarDatas(this.cronapi.conversion.stringToDate(this.cronapi.dateTime.formatDateTime(this.cronapi.dateTime.incHour(this.cronapi.screen.getValueOfField("vars.dataAgendamento"), 3), 'DD/MM/YYYY')), this.cronapi.conversion.stringToDate(this.cronapi.dateTime.formatDateTime(this.cronapi.dateTime.getNow(), 'DD/MM/YYYY')));
  if (existente == 0 && !this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("vars.dataAgendamento")) && isValid) {
    listaDatas.push(this.cronapi.dateTime.formatDateTime(this.cronapi.dateTime.incHour(this.cronapi.screen.getValueOfField("vars.dataAgendamento"), 3), 'DD/MM/YYYY'));
  } else {
    this.cronapi.screen.notify('warning','Favor inserir data não repetida maior ou igual a atual e com limite de um ano.');
  }
  this.cronapi.screen.createScopeVariable('listaDatas', listaDatas);
  return listaDatas;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Agendamento.removerMes = function(mes, anoMes) {
 var item, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, maiorData, menorData, numeroSemana, objAgendamento, param, retorno;
  listaMesAdd = this.cronapi.screen.getScopeVariable('listaMeses');
  count = 1;
  for (var j_index in listaMesAdd) {
    j = listaMesAdd[j_index];
    if (String(this.cronapi.object.getProperty(j, 'value')) + String(this.cronapi.object.getProperty(j, 'anoValue')) == String(mes) + String(anoMes)) {
      listaMesAdd.splice((count - 1), 1);
    }
    count = count + 1;
  }
  this.cronapi.screen.createScopeVariable('listaMeses', listaMesAdd);
  return listaMesAdd;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Agendamento.validarDatas = function(maiorData, menorData) {
 var item, anoMes, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, mes, numeroSemana, objAgendamento, param, retorno;
  isValid = this.cronapi.dateTime.getDaysBetweenDates(maiorData, menorData) >= 0 && this.cronapi.dateTime.getDaysBetweenDates(maiorData, menorData) <= 366;
  return isValid;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Agendamento.concluirAgendamento = function() {
 var item, anoMes, contador, count, existente, i, isValid, j, listaDatas, listaMesAdd, maiorData, menorData, mes, numeroSemana, objAgendamento, param, retorno;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  contador = 0;
  for (var i_index in listaDatas) {
    i = listaDatas[i_index];
    numeroSemana = this.cronapi.myfunctions.obterNumeroSemana(this.cronapi.conversion.stringToDate(i));
    objAgendamento = this.cronapi.object.newObject({name: 'horario_Escala', value: this.cronapi.screen.getValueOfField("HorarioEscala.active.id")}, {name: 'medico', value: this.cronapi.screen.getValueOfField("Medico.active.id")}, {name: 'dt_agenda_age', value: this.cronapi.conversion.stringToDate(i)}, {name: 'cd_status_age', value: this.cronapi.screen.getValueOfField("vars.status")}, {name: 'qt_mudancas_age', value: this.cronapi.screen.getValueOfField("vars.qtdeMudancas")}, {name: 'startsAt', value: this.cronapi.conversion.stringToDate(i)}, {name: 'endsAt', value: this.cronapi.conversion.stringToDate(i)}, {name: 'local_Plantao', value: this.cronapi.screen.getValueOfField("LocalPlantao.active.id")}, {name: 'corSemana', value: numeroSemana});
    this.cronapi.util.callServerBlocklyAsynchronous('blockly.Agenda:inserirAgendamento', function(sender_retorno) {
        retorno = sender_retorno;
      if (retorno) {
        contador = contador + 1;
      } else {
        this.cronapi.screen.notify('error',String('Falha ao inserir agendamento. Data: ') + String(i));
      }
      if (listaDatas.length == contador) {
        this.cronapi.screen.notify('success','Agendamento Realizado com Sucesso.');
        this.cronapi.screen.changeView("#/home/admin/agenda",[  ]);
      }
    }.bind(this), objAgendamento);
  }
}
