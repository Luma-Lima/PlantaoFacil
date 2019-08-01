(function() {
  'use strict';

  this.cronapi = this.cronapi || {};

   /**
   * @categoryName Minhas Funções
   */
  this.cronapi.myfunctions = this.cronapi.myfunctions || {};
  
  /**
   * @type function
   * @name obterNumeroSemana
   * @description Obter Número da Semana
   * @multilayer false
   * @param {ObjectType.STRING} input Param Description
   * @returns {ObjectType.STRING}
   */
  this.cronapi.myfunctions.obterNumeroSemana = function(/** @type {ObjectType.STRING} @description Parâmetro: Descrição do parâmetro */paramDate) {
    
      var month = paramDate.getMonth()
          , year = paramDate.getFullYear()
          , firstWeekday = new Date(year, month, 1).getDay()
          , lastDateOfMonth = new Date(year, month + 1, 0).getDate()
          , offsetDate = paramDate.getDate() + firstWeekday - 1
          , index = 1
          , weeksInMonth = index + Math.ceil((lastDateOfMonth + firstWeekday - 7) / 7)
          , week = index + Math.floor(offsetDate / 7)
        ;
      if (week < 2 + index) return week;
      return week === weeksInMonth ? index + 5 : week;
  };
  

}).bind(window)();