window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.OuvinteNotificacao = window.blockly.js.blockly.OuvinteNotificacao || {};

/**
 * OuvinteNotificacao
 */
window.blockly.js.blockly.OuvinteNotificacao.iniciar = function() {
 var item, dados, token, deviceUUID, firebaseTokenCode, devicePlatformVersion, deviceModel, devicePlatform, deviceAppVersion, deviceAppName, usuarioDestinoId, agendaId;
  firebaseTokenCode = this.cronapi.util.executeJavascriptReturn('window.firebaseTokenCode ? window.firebaseTokenCode : \'\';');
  deviceUUID = this.cronapi.util.executeJavascriptReturn('window.deviceUUID ? window.deviceUUID : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(deviceUUID)) {
    deviceUUID = this.cronapi.cordova.device.getDeviceInfo('uuid');
  }
  deviceAppName = this.cronapi.util.executeJavascriptReturn('window.deviceAppName ? window.deviceAppName : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(deviceAppName)) {
    deviceAppName = this.cronapi.util.executeJavascriptReturn('window.BuildInfo ? window.BuildInfo.packageName : \'\';');
  }
  deviceAppVersion = this.cronapi.util.executeJavascriptReturn('window.deviceAppVersion ? window.deviceAppVersion : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(deviceAppVersion)) {
    deviceAppVersion = this.cronapi.util.executeJavascriptReturn('window.BuildInfo ? window.BuildInfo.version : \'\';');
  }
  devicePlatform = this.cronapi.util.executeJavascriptReturn('window.devicePlatform ? window.devicePlatform : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(devicePlatform)) {
    devicePlatform = this.cronapi.cordova.device.getDeviceInfo('platform');
  }
  devicePlatformVersion = this.cronapi.util.executeJavascriptReturn('window.devicePlatformVersion ? window.devicePlatformVersion : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(devicePlatformVersion)) {
    devicePlatformVersion = this.cronapi.cordova.device.getDeviceInfo('version');
  }
  deviceModel = this.cronapi.util.executeJavascriptReturn('window.deviceModel ? window.deviceModel : \'\';');
  if (this.cronapi.logic.isNullOrEmpty(deviceModel)) {
    deviceModel = this.cronapi.cordova.device.getDeviceInfo('model');
  }
  dados = this.cronapi.object.createObjectFromString('{}');
  this.cronapi.object.setProperty(dados, 'appName', deviceAppName);
  this.cronapi.object.setProperty(dados, 'appVersion', deviceAppVersion);
  this.cronapi.object.setProperty(dados, 'token', firebaseTokenCode);
  this.cronapi.object.setProperty(dados, 'platform', devicePlatform);
  this.cronapi.object.setProperty(dados, 'model', deviceModel);
  this.cronapi.object.setProperty(dados, 'platformVersion', devicePlatformVersion);
  this.cronapi.object.setProperty(dados, 'uuid', deviceUUID);
  if (this.cronapi.logic.isNullOrEmpty(firebaseTokenCode)) {
    this.cronapi.cordova.device.getFirebaseToken(function(sender_token) {
        token = sender_token;
      this.cronapi.object.setProperty(dados, 'token', token);
      this.cronapi.util.callServerBlocklyNoReturn('blockly.Notificacao:gravarDispositivo', dados);
      this.cronapi.screen.notify('success',['uuid:',deviceUUID,'token:',token.slice(0, 8)].join(''));
    }.bind(this), function(sender_item) {
        item = sender_item;
    }.bind(this));
  } else {
    this.cronapi.util.callServerBlocklyNoReturn('blockly.Notificacao:gravarDispositivo', dados);
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OuvinteNotificacao.solicitarMudancaAgenda = function(agendaId, usuarioDestinoId) {
 var item, dados, token, deviceUUID, firebaseTokenCode, devicePlatformVersion, deviceModel, devicePlatform, deviceAppVersion, deviceAppName;
  this.cronapi.util.callServerBlocklyNoReturn('blockly.Notificacao:solicitarMudancaAgenda', agendaId, usuarioDestinoId);
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OuvinteNotificacao.ouvir = function() {
 var item, dados, token, deviceUUID, firebaseTokenCode, devicePlatformVersion, deviceModel, devicePlatform, deviceAppVersion, deviceAppName, usuarioDestinoId, agendaId;
  this.cronapi.cordova.device.getFirebaseNotificationData(function(sender_dados) {
      dados = sender_dados;
    this.cronapi.screen.notify('success',dados);
  }.bind(this), function(sender_dados) {
      dados = sender_dados;
  }.bind(this));
}
