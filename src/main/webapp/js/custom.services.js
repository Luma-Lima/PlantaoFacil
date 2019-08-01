app.factory('PlantaoAPIService', function($http, calendarConfig, Configurations){

  var actions = [{
    label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
    onClick: function(args) {
      alert.show('Edited', args.calendarEvent);
    }
  }, {
    label: '<i class=\'glyphicon glyphicon-remove\'></i>',
    onClick: function(args) {
      alert.show('Deleted', args.calendarEvent);
    }
  }];

   function buscarDados(){
     return $http({
              method: 'GET',
              url:  Configurations.getApiServer() + '/api/cronapi/rest/blockly.BuscarDados:ExecutarBuscarDados?page=0&size=10000',
              headers: {
                 'Content-Type': 'application/json'
               }
          });
  };

      return {
        getData: function() {
            let responseData;
            let calendarData = [];
            return buscarDados().then(function successCallback(response) {
              responseData = response.data;
              for (i = 0; i < responseData.length; i++) {
                var day = 60 * 60 * 24 * 1000;
                calendarData.push({
                  title: responseData[i].title,
                  medico: responseData[i].medico,
                  agendaMedicoId: responseData[i].agendaMedicoId,
                  responsavel: responseData[i].responsavel,
                  horario: responseData[i].horarioEscala,
                  horarioEscalaId: responseData[i].horarioEscalaId,
                  local: responseData[i].local,
                  color: responseData[i].color,
                  startsAt: new Date(responseData[i].startsAt + day),
                  endsAt: new Date(responseData[i].endsAt + day),
                  draggable: responseData[i].draggable,
                  resizable: responseData[i].resizable,
                  actions: responseData[i].actions,
                  agendaId: responseData[i].agendaId,
              }) 
              }
              return calendarData;
            }, function errorCallback(response) {
              return response;
            })
        },
        getRealData: function(){
          return $http.get('https://jsonplaceholder.typicode.com/todos/')
        }
      }
    
});   

app.factory('paintService', function(){

  var alreadyPainted = false;

   var paintWeeksDynamically = function(referenceDate, momentDate){
        angular.element('.cor-do-plantao').each(function(index, el){
              //colorir dinamicamente por semana após dados serem carregados
          momentDate.set("date", (index * 7) + 1);
              var weekNumber = Math.abs(momentDate.diff(referenceDate, 'week'));
              var initialWeekColor = weekNumber % 4;
              var cor = '';
              var marcador = index + 1;
              switch(initialWeekColor){

                  case 1:
                    cor = '#66CCFF';
                    break;        
                  case 2:
                   cor = '#FF9999';
                    break;     
                  case 3:
                    cor = '#00CC33';
                    break;
                  default:
                    cor = '#B2B2B2';
                
                };
              angular.element('#week' + marcador).css('background', cor);
              angular.element(el).css('background', cor);
        });         
      };

      var setMomentDate = function(momentDate){
        this.momentDate = momentDate;
      };

      var getMomentDate = function(){
        return this.momentDate;
      };

      var setReferenceDate = function(referenceDate){
        this.referenceDate = referenceDate;
      };

      var getReferenceDate = function(){
        return this.referenceDate;
      };

      var setAlreadyPainted = function(value){
        alreadyPainted = value;
      }

      var getPaintedStatus = function(){
        return alreadyPainted;
      }

      return {
        paintWeeksDynamically: paintWeeksDynamically,
        setMomentDate: setMomentDate,
        getMomentDate: getMomentDate,
        setReferenceDate: setReferenceDate,
        getReferenceDate: getReferenceDate,
        setAlreadyPainted: setAlreadyPainted,
        getPaintedStatus: getPaintedStatus
      };
});