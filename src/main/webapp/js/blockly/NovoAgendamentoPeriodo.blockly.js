window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.NovoAgendamentoPeriodo = window.blockly.js.blockly.NovoAgendamentoPeriodo || {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.setMesFinalComboAutomatico = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  numeroMeses = this.cronapi.screen.getValueOfField("vars.comboMeses");
  if (this.cronapi.logic.isNullOrEmpty(numeroMeses)) {
    numeroMeses = 0;
  }
  this.cronapi.screen.changeValueOfField("vars.mesAgendamentoFinal", this.cronapi.dateTime.incMonth(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), numeroMeses));
  this.blockly.js.blockly.NovoAgendamentoPeriodo.validarMesAntigo();
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.validarHoraAntiga = function(dataDeAgendamento, horarioEscala) {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  horaPermitida = false;
  dataAtual = this.cronapi.dateTime.getNow();
  horaDaData = this.cronapi.dateTime.getHour(this.cronapi.dateTime.incHour(dataAtual, -3));
  diffDiasDatas = this.cronapi.dateTime.getDaysBetweenDates(this.cronapi.dateTime.incDay(dataDeAgendamento, 1), this.cronapi.dateTime.getNow());
  textoHoraInicial = horarioEscala.slice(0, 2);
  if (diffDiasDatas <= 0) {
    if (textoHoraInicial == '07' && this.cronapi.conversion.toLong(horaDaData) >= 7) {
      this.cronapi.screen.notify('warning','Não é possível agendar no horáro de 07h para a data de hoje.');
    } else if (textoHoraInicial == '13' && this.cronapi.conversion.toLong(horaDaData) >= 13) {
      this.cronapi.screen.notify('warning','Não é possível agendar no horáro de 13h para a data de hoje.');
    } else if (textoHoraInicial == '19' && (this.cronapi.conversion.toLong(horaDaData) >= 19 || this.cronapi.conversion.toLong(horaDaData) < 7)) {
      this.cronapi.screen.notify('warning','Não é possível agendar no horáro de 19h para a data de hoje.');
    } else {
      horaPermitida = true;
    }
  } else {
    horaPermitida = true;
  }
  return horaPermitida;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.colorirSemana = function(inputDataAtual) {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  dataDeReferencia = this.cronapi.dateTime.newDate(2018, 1, 1, 0, 0, 0);
  inputDataAtual = this.cronapi.dateTime.newDate(this.cronapi.dateTime.getYear(inputDataAtual), this.cronapi.dateTime.getMonth(inputDataAtual), this.cronapi.dateTime.getDay(inputDataAtual), 0, 0, 0);
  diffDiasDatas = this.cronapi.dateTime.getDaysBetweenDates(inputDataAtual, dataDeReferencia);
  numeroSemanas = Math.floor(diffDiasDatas / 7);
  indiceSemana = numeroSemanas % 4;
  if (indiceSemana == 1) {
    cor = '#FF9999';
  }
  if (indiceSemana == 2) {
    cor = '#00CC33';
  }
  if (indiceSemana == 3) {
    cor = '#B2B2B2';
  }
  if (indiceSemana == 0) {
    cor = '#66CCFF';
  }
  this.cronapi.screen.changeAttrValue("texto-cor-semana", 'style', ['','height: 40px;','background-color:',cor].join(''));
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.limparListaDatas = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  listaDatas = [];
  this.cronapi.screen.createScopeVariable('listaDatas', listaDatas);
}

/**
 * NovoAgendamentoPeriodo
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.setDataAtual = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  this.cronapi.screen.changeValueOfField("vars.dataAgendamentoInicial", this.cronapi.dateTime.getNow());
  this.cronapi.screen.changeValueOfField("vars.mesAgendamentoFinal", this.cronapi.dateTime.getNow());
  this.blockly.js.blockly.NovoAgendamentoPeriodo.colorirSemana(this.cronapi.dateTime.getNow());
  this.cronapi.screen.createScopeVariable('listaDatas', [this.cronapi.dateTime.formatDateTime(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), 'DD/MM/YYYY')]);
  item = this.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiasAutomatico();
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiaAgendamento = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  if (this.cronapi.logic.isNullOrEmpty(listaDatas)) {
    listaDatas = [];
  }
  existente = listaDatas.indexOf(this.cronapi.dateTime.formatDateTime(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), 'DD/MM/YYYY')) + 1;
  if (existente == 0 && !this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"))) {
    listaDatas.push(this.cronapi.dateTime.formatDateTime(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), 'DD/MM/YYYY'));
  } else {
  }
  this.cronapi.screen.createScopeVariable('listaDatas', listaDatas);
  return listaDatas;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiasAutomatico = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  listaDatas = [];
  mesFinal = this.cronapi.dateTime.incMonth(this.cronapi.screen.getValueOfField("vars.mesAgendamentoFinal"), 1);
  mesFinal = this.cronapi.dateTime.newDate(this.cronapi.dateTime.getYear(mesFinal), this.cronapi.dateTime.getMonth(mesFinal), 1, 0, 0, 0);
  if (this.cronapi.logic.isNullOrEmpty(listaDatas)) {
    listaDatas = [];
  }
  dataDaMatrizSemanal = this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial");
  while (this.cronapi.dateTime.getDaysBetweenDates(mesFinal, dataDaMatrizSemanal) > 1) {
    existente = listaDatas.indexOf(this.cronapi.dateTime.formatDateTime(dataDaMatrizSemanal, 'DD/MM/YYYY')) + 1;
    if (existente == 0 && !this.cronapi.logic.isNullOrEmpty(dataDaMatrizSemanal)) {
      listaDatas.push(this.cronapi.dateTime.formatDateTime(dataDaMatrizSemanal, 'DD/MM/YYYY'));
    }
    dataDaMatrizSemanal = this.cronapi.dateTime.incDay(dataDaMatrizSemanal, 28);
  }
  if (this.cronapi.dateTime.getHoursBetweenDates(mesFinal, dataDaMatrizSemanal) < 23 && this.cronapi.dateTime.getHoursBetweenDates(mesFinal, dataDaMatrizSemanal) > 0) {
    existente = listaDatas.indexOf(this.cronapi.dateTime.formatDateTime(dataDaMatrizSemanal, 'DD/MM/YYYY')) + 1;
    if (existente == 0 && !this.cronapi.logic.isNullOrEmpty(dataDaMatrizSemanal)) {
      listaDatas.push(this.cronapi.dateTime.formatDateTime(dataDaMatrizSemanal, 'DD/MM/YYYY'));
    }
  }
  this.cronapi.screen.createScopeVariable('listaDatas', listaDatas);
  return listaDatas;
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.validarDataAntiga = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  this.cronapi.screen.changeValueOfField("vars.mesAgendamentoFinal", this.cronapi.dateTime.incMonth(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), numeroMeses));
  this.cronapi.screen.changeValueOfField("vars.comboMeses", 0);
  dataAgendamentoInicial = this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial");
  dataAtual = this.cronapi.dateTime.getNow();
  this.blockly.js.blockly.NovoAgendamentoPeriodo.colorirSemana(dataAgendamentoInicial);
  diffDiasDatasInicial = this.cronapi.dateTime.getDaysBetweenDates(dataAgendamentoInicial, dataAtual);
  if (diffDiasDatasInicial < 0) {
    this.cronapi.screen.notify('error','Não é possível selecionar uma data anterior à atual.');
    this.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas(dataAtual);
  }
  diffAnos = this.cronapi.dateTime.getYearsBetweenDates(dataAgendamentoInicial, dataAtual);
  if (diffAnos > 0) {
    this.cronapi.screen.notify('error','Não é possível agendar para um período após 1 ano');
    this.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas(dataAtual);
  }
  if (diffAnos <= 0 && diffDiasDatasInicial >= 0) {
    listaDatas = this.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiaAgendamento();
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.concluirAgendamento = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  listaDatas = this.cronapi.screen.getScopeVariable('listaDatas');
  contador = 0;
  tamanhoLista = listaDatas.length;
  if (this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("Medico.active.id")) || this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("HorarioEscala.active.id")) || this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getValueOfField("LocalPlantao.active.id"))) {
    this.cronapi.screen.notify('warning','Favor preencher todos os campos');
  } else {
    if (tamanhoLista == 0) {
      this.cronapi.screen.notify('warning','Favor inserir uma ou mais datas para agendamento');
    } else {
      for (var i_index in listaDatas) {
        i = listaDatas[i_index];
        agendamentoHoraAntiga = this.blockly.js.blockly.NovoAgendamentoPeriodo.validarHoraAntiga(this.cronapi.conversion.stringToDate(i), this.cronapi.screen.getValueOfField("HorarioEscala.active.nm_horario_hre"));
        if (agendamentoHoraAntiga == true) {
          objAgendamento = this.cronapi.object.newObject({name: 'horario_Escala', value: this.cronapi.screen.getValueOfField("HorarioEscala.active.id")}, {name: 'medico', value: this.cronapi.screen.getValueOfField("Medico.active.id")}, {name: 'dt_agenda_age', value: this.cronapi.conversion.stringToDate(i)}, {name: 'cd_status_age', value: this.cronapi.screen.getValueOfField("vars.status")}, {name: 'qt_mudancas_age', value: this.cronapi.screen.getValueOfField("vars.qtdeMudancas")}, {name: 'startsAt', value: this.cronapi.conversion.stringToDate(i)}, {name: 'endsAt', value: this.cronapi.conversion.stringToDate(i)}, {name: 'local_Plantao', value: this.cronapi.screen.getValueOfField("LocalPlantao.active.id")});
          this.cronapi.util.callServerBlocklyAsynchronous('blockly.Agenda:consultarAgendamento', function(sender_dataResultJson) {
              dataResultJson = sender_dataResultJson;
            existeRegistro = this.cronapi.json.getProperty(dataResultJson, 'existeRegistro');
            dt_agenda_age = this.cronapi.json.getProperty(dataResultJson, 'dt_agenda_age');
            if (existeRegistro == false) {
              contador = contador + 1;
            } else {
              this.cronapi.screen.notify('error',String('Falha ao inserir agendamento. Já existe um agendamento para a data: ') + String(this.cronapi.dateTime.formatDateTime(dt_agenda_age, 'DD/MM/YYYY')));
            }
            if (contador == listaDatas.length) {
              contador = 0;
              for (var j_index in listaDatas) {
                j = listaDatas[j_index];
                objAgendamento = this.cronapi.object.newObject({name: 'horario_Escala', value: this.cronapi.screen.getValueOfField("HorarioEscala.active.id")}, {name: 'medico', value: this.cronapi.screen.getValueOfField("Medico.active.id")}, {name: 'dt_agenda_age', value: this.cronapi.conversion.stringToDate(j)}, {name: 'cd_status_age', value: this.cronapi.screen.getValueOfField("vars.status")}, {name: 'qt_mudancas_age', value: this.cronapi.screen.getValueOfField("vars.qtdeMudancas")}, {name: 'startsAt', value: this.cronapi.conversion.stringToDate(j)}, {name: 'endsAt', value: this.cronapi.conversion.stringToDate(j)}, {name: 'local_Plantao', value: this.cronapi.screen.getValueOfField("LocalPlantao.active.id")});
                this.cronapi.util.callServerBlocklyAsynchronous('blockly.Agenda:inserirAgendamento', function(sender_retorno) {
                    retorno = sender_retorno;
                  if (retorno == true) {
                    this.cronapi.screen.showModal("modal43339");
                  }
                }.bind(this), objAgendamento);
              }
            }
          }.bind(this), objAgendamento);
        }
      }
    }
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas = function(dataInicial) {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  this.cronapi.screen.createScopeVariable('listaDatas', []);
  this.cronapi.screen.changeValueOfField("vars.mesAgendamentoFinal", this.cronapi.dateTime.getNow());
  this.cronapi.screen.changeValueOfField("vars.dataAgendamentoInicial", this.cronapi.dateTime.getNow());
  this.cronapi.screen.changeValueOfField("vars.comboMeses", 0);
  this.cronapi.screen.createScopeVariable('listaDatas', [this.cronapi.dateTime.formatDateTime(this.cronapi.screen.getValueOfField("vars.dataAgendamentoInicial"), 'DD/MM/YYYY')]);
  item = this.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiasAutomatico();
  this.blockly.js.blockly.NovoAgendamentoPeriodo.colorirSemana(dataInicial);
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.validarMesAntigo = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  mesAgendamentoFinal = this.cronapi.screen.getValueOfField("vars.mesAgendamentoFinal");
  dataAtual = this.cronapi.dateTime.getNow();
  diffDiasDatasFinal = this.cronapi.dateTime.getMonthsBetweenDates(mesAgendamentoFinal, dataAtual);
  if (diffDiasDatasFinal < 0) {
    this.cronapi.screen.notify('error','Não é possível agendar para um mês anterior ao atual.');
    this.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas(dataAtual);
  }
  diffAnos = this.cronapi.dateTime.getYearsBetweenDates(mesAgendamentoFinal, dataAtual);
  if (diffAnos > 0) {
    this.cronapi.screen.notify('error','Não é possível agendar para um período após 1 ano');
    this.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas(dataAtual);
  }
  if (diffAnos <= 0 && diffDiasDatasFinal >= 0) {
    listaDatasAgendamento = this.blockly.js.blockly.NovoAgendamentoPeriodo.adicionarDiasAutomatico();
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.voltaCrudAgenda = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  this.cronapi.screen.hideModal("modal43339");
  this.cronapi.util.executeJavascriptNoReturn('$(\'.modal-backdrop\').hide()');
  this.cronapi.screen.notify('success','Agendamento Realizado com Sucesso.');
  this.cronapi.screen.changeView("#/home/admin/agenda",[  ]);
  this.cronapi.screen.hideModal("modal43339");
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.NovoAgendamentoPeriodo.agendarNovoMedico = function() {
 var item, agendamentoHoraAntiga, contador, cor, dataAgendamentoInicial, dataAtual, dataDaMatrizSemanal, dataDeAgendamento, dataDeReferencia, dataInicial, dataResultJson, diffAnos, diffDiasDatas, diffDiasDatasFinal, diffDiasDatasInicial, dt_agenda_age, existente, existeRegistro, horaDaData, horaPermitida, horarioEscala, i, indiceSemana, inputDataAtual, j, listaDatas, listaDatasAgendamento, mesAgendamentoFinal, mesFinal, numeroMeses, numeroSemanas, objAgendamento, retorno, tamanhoLista, textoHoraInicial;
  this.cronapi.screen.hideModal("modal43339");
  this.cronapi.screen.notify('success','Agendamento Realizado com Sucesso.');
  this.blockly.js.blockly.NovoAgendamentoPeriodo.resetarDatas(this.cronapi.dateTime.getNow());
  this.cronapi.screen.changeValueOfField("vars.horarioEscala", '');
  this.cronapi.screen.changeValueOfField("vars.localPlantao", '');
}
