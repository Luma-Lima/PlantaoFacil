window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.ChamarListarMedico = window.blockly.js.blockly.ChamarListarMedico || {};

/**
 * ChamarListarMedico
 */
window.blockly.js.blockly.ChamarListarMedico.ExecutarChamarListarMedico = function() {

  this.cronapi.screen.showComponent("comboMedico");
  this.cronapi.screen.showComponent("AlterarPlantaoMedico");
}
