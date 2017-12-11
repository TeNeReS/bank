var bankApp = angular.module('bankApp', ['ngRoute']);

bankApp.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl:'template/home.html',
            controller:'mainController'
        })
        .when('/persons',{
            templateUrl:'template/persons.html',
            controller:'personsController'
        })
        .when('/transactions',{
            templateUrl:'template/transactions.html',
            controller:'transactionsController'
        })
        .otherwise({
            redirectTo:'/'
        });
});

bankApp.controller('mainController', function($scope) {

});

bankApp.controller('personsController', function($scope) {

});

bankApp.controller('transactionsController', function($scope) {

});