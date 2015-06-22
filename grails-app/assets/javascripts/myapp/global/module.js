//= require_self

'use strict';

angular.module('myapp.global', [ 'grails' ])
	.factory('globalErrorHandler', ['$q', '$log', 'FlashService', function($q, $log, FlashService) {
		
		return {
			response : function(response) {
				$log.debug('done ajax!');
				return response || $q.when(response);
		    },
			
			responseError: function(rejection) {
				console.log('bad things')
				console.error(rejection.status)
				
	            return $q.reject(rejection);
	        }
		};
	}])
	.config(['$httpProvider', function($httpProvider) {
		$httpProvider.interceptors.push('globalErrorHandler');
	}])
;

	
