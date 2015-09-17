//= require_self
//= require_tree /templates/grails/directives/fields

'use strict';

function fieldContainer() {
    return {
        replace: true,
        transclude: true,
        scope: {
            type: '@',
            label: '@',
            invalid : '='
        },
        link: function($scope, $element) {
//            var field = ($element.find('input').length > 0) ? $element.find('input') : $element.find('select');
        	var field = $element.find('input');
            if(!field.length) { field = $element.find('select'); }
            if(!field.length) { field = $element.find('textarea'); }
            field.addClass('form-control');
        },
        templateUrl: '/grails/directives/fields/field-container.html'
    }
}

function groupContainer() {
    return {
        replace: true,
        transclude: true,
        scope: {
            type: '@',
            label: '@',
            invalid : '='
        },
        link: function($scope, $element) {
//            var field = ($element.find('input').length > 0) ? $element.find('input') : $element.find('select');
        	var field = $element.find('input');
            if(!field.length) { field = $element.find('select'); }
            if(!field.length) { field = $element.find('textarea'); }
            field.addClass('form-control');
        },
        templateUrl: '/grails/directives/fields/group-container.html'
    }
}


function displayField() {
    return {
        restrict: 'EA',
        replace: true,
        scope: {
            label: '@',
            value: '='
        },
        templateUrl: '/grails/directives/fields/display-field.html'
    }
}

function dateField() {
    return {
        replace: true,
        link: function($scope) {

            $scope.open = function() {
                $scope.opened = true;
            };

        },
        templateUrl: '/grails/directives/fields/date-field.html'
    }
}

angular.module('grails.directives.fields', ['ui.bootstrap'])
	.directive("groupContainer", groupContainer)
    .directive("fieldContainer", fieldContainer)
    .directive("displayField", displayField)
    .directive("dateField", dateField);