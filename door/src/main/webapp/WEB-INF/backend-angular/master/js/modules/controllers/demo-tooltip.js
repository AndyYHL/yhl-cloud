/**=========================================================
 * Module: demo-tooltip.js
 * Provides a simple demo for tooltip
 =========================================================*/
AppH.controller('TooltipDemoCtrl', ['$scope', function ($scope) {

  $scope.dynamicTooltip = 'Hello, World!';
  $scope.dynamicTooltipText = 'dynamic';
  $scope.htmlTooltip = 'I\'ve been made <b>bold</b>!';

}]);