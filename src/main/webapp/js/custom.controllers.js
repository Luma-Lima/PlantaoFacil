app.controller('AfterLoginController', function($scope) {
 //
});

app.controller('AfterHomeController', function($scope) {
 //
});

app.controller('AfterPageController', function($scope) {
 //
});

app.controller('CalendarCtrl', function($scope, moment, alert, calendarConfig, PlantaoAPIService, $http, Configurations, paintService) {

      var locale = (window.navigator.userLanguage || window.navigator.language || 'pt_br').toLowerCase();

      moment.locale(locale, {
        week : {
            dow : 1, // Monday is the first day of the week.
        }
      });

      calendarConfig.dateFormatter = 'moment';
      var vm = this;

      //These variables MUST be set as a minimum for the calendar to work
      vm.calendarView = 'month';
      vm.viewDate = new Date();

      paintService.setReferenceDate(moment("2017-10-01"))
      paintService.setMomentDate(moment(vm.viewDate));

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

      
      vm.setColor = function(event){
        //console.log(event);
      }
      
      // POPULANDO O CALENDÁRIO
      
      /**
        PlantaoAPIService.getRealData(), é possível consumir as informações 
        do banco de dados quando implementado, para isso,
        é necessário atribuir a resposta da requisição a variável de escopo, vm.events (aplicação web)
        vm.plantoes (aplicação mobile)      
      */
      
      // * Populando o calendário com dados mokados de um service *
      PlantaoAPIService.getData().then(function(dados) {
        vm.events = dados;
        paintService.paintWeeksDynamically(paintService.getReferenceDate(), paintService.getMomentDate());
      });

      vm.addEvent = function() {
        vm.events.push({
          title: 'New event',
          startsAt: moment().startOf('day').toDate(),
          endsAt: moment().endOf('day').toDate(),
          color: calendarConfig.colorTypes.important,
          draggable: true,
          resizable: true
        });
      };
      
      
      // * Exemplo de requisição real a base de dados *
      
      // PlantaoAPIService.getRealData()
      //   .then(function(res){
      //     console.log(res)
      //   }, function(err){
      //     console.log(err)
      //   });

      vm.cellIsOpen = true;
  
      vm.updateWeekCollors = function(){
        //console.log(vm.semanas);
      }
  
      vm.eventClicked = function(event) {
        vm.currentEvent = event;
        
        let convertDate = {
          inicio: new Date(vm.currentEvent.startsAt),
          final: new Date(vm.currentEvent.endsAt),
        };
        
        vm.currentEvent.convertDates  = {
          inicio: moment(convertDate.inicio).format('DD/MM/YYYY'),
          final: moment(convertDate.final).format('DD/MM/YYYY')
        };
        
        angular.element('#plantaoModal').modal('show');
      };
  
      vm.eventEdited = function(event) {
        alert.show('Edited', event);
      };
  
      vm.eventDeleted = function(event) {
        alert.show('Deleted', event);
      };
  
      vm.eventTimesChanged = function(event) {
        alert.show('Dropped or resized', event);
      };
  
      vm.toggle = function($event, field, event) {
        $event.preventDefault();
        $event.stopPropagation();
        event[field] = !event[field];
      };
  
      vm.timespanClicked = function(date, cell) {
  
        if (vm.calendarView === 'month') {
          if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
            vm.cellIsOpen = false;
          } else {
            vm.cellIsOpen = true;
            vm.viewDate = date;
          }
        } else if (vm.calendarView === 'year') {
          if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
            vm.cellIsOpen = false;
          } else {
            vm.cellIsOpen = true;
            vm.viewDate = date;
          }
        }
  
      };

   });
    
  app.factory('alert', function($modal) {
    function show(action, event) {
      return $modal.open({
        templateUrl: './modalContent.html',
        controller: function() {
          var vm = this;
          vm.action = action;
          vm.event = event;
          vm.medico = "Dr. Pablo Mendes"
        },
        controllerAs: 'vm'
      });
    }

    return {
      show: show
    };
  });