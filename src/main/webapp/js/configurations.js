app.factory('Configurations', [ 
  function() {
    var _baseUrl = obterUrlBase();


    function getApiServer() {
      return _baseUrl;
    } 
    
    function obterUrlBase (){
      let url = window.location.href;
      let arr = url.split("/");
      return arr[0] + "//" + arr[2];
    }
    
    return {
      getApiServer: getApiServer
    };

}]);