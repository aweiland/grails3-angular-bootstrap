/**
 * Inline controller
 */
angular.module('myapp.accordian.controllers', []) //'grails', 'myapp.global'
    .controller('AccordianDemoCtrl', function($scope, FlashService) {
        $scope.groups = [
            { title: 'Header 1', content: 'Content 1'},
            { title: 'Header 2', content: 'Content 2'}
        ];

        $scope.items = [ 'Item 1', 'Item 2', 'Item 3' ]
        FlashService.error('Flash Service Test');
    });