(function(){
	'use strict';
	
	angular.module('kenzanApp', [])
	       .controller('mainController', ['$scope', function($scope) {
	    	   $scope.message = 'This is a new app!';
	       }]);
})();