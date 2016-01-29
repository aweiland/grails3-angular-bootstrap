// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require /webjars/jquery/2.2.0/jquery.js
//= require /webjars/angularjs/1.4.9/angular
//= require /webjars/angularjs/1.4.9/angular-route
//= require /webjars/angularjs/1.4.9/angular-resource
//= require /webjars/angularjs/1.4.9/angular-animate
//= require /webjars/bootstrap/3.3.6/js/bootstrap
//= require /webjars/angular-ui-bootstrap/1.1.1-1/ui-bootstrap
// require_tree grails
//= require_tree myapp
// require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}


angular.module('myapp', [
    //'grails',
    //'myapp.accordian'
]);
