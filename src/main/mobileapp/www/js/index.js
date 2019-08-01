var device = { 
  init: function() {
    document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
  },
  onDeviceReady: function() {
    // push notification 
    var firebase = window.FirebasePlugin;
    var hostapp = window.hostApp;
    var device = window.device;
    var build = window.BuildInfo;
    
    // get token firebase
    if (firebase && hostapp && device) {
      var appURL = hostapp + 'device';
      
      window.deviceUUID = device.uuid; 
      window.deviceAppName = build.packageName;
      window.deviceAppVersion = build.version;
      window.devicePlatform = device.platform; 
      window.devicePlatformVersion = device.version;
      window.deviceModel = device.model;

      console.log('device: ' + JSON.stringify(device));
      
      debugger;
      if (!window.firebaseTokenCode) {
        firebase.getToken(function(code) {
          debugger;
          window.firebaseTokenCode = code;
          console.log(code ? 'token OK!' : 'token failure.');
          /*var data  = { id : device.uuid, 
                        appName : build.packageName,
                        appVersion : build.version,
                        platform : device.platform, 
                        platformVersion : device.version,
                        model : device.model, 
                        token : code };
          console.log('Received Token: ' + (code ? 'OK' :'NOK'));
          $.post(appURL, data).done(function(d) {
            console.log('Received Event: devicesend');
          });
          */
        }.bind(this), 
        function(err) {
          console.error(err);
        });
      }      
    }
  }
};
device.init();