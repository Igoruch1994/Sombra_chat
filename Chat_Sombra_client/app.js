
var app = angular.module('myApp', ['ngRoute']);

app.filter('reverse', function() {
    return function(items) {
        return items.slice().reverse();
    };
});

app.config(function($routeProvider) {


    $routeProvider

        .when('/', {
            templateUrl : 'html/home.html',
            controller  : 'HomeController'
        })
        .when('/registration', {
            templateUrl : 'html/registration.html',
            controller  : 'RegistrationController'
        })
        .when('/chat', {
            templateUrl : 'html/chat.html',
            controller  : 'chatController'
        })
        .when('/login', {
            templateUrl : 'html/login.html'
        })
        .otherwise({redirectTo: '/'});

});




