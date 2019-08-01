(function() {
  'use strict';

  this.cronapi = this.cronapi || {};

   /**
   * @categoryName Minhas Funções
   */
  this.cronapi.myfunctions = this.cronapi.myfunctions || {};
  
  /**
   * @type function
   * @name Converter Millisegundos Para Data
   * @description Converter Millisegundos Para Data
   * @multilayer false
   * @param {ObjectType.LONG} input Param Description
   * @returns {ObjectType.DATE}
   */
  this.cronapi.myfunctions.converterMillisegundosParaData = function(/** @type {ObjectType.STRING} @description Parâmetro: Descrição do parâmetro */input) {
    return moment.utc(input).format("DD/MM/YYYY");
  };
  

}).bind(window)();