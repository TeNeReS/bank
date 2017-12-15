var bankApp = angular.module('bankApp', ['ngRoute', 'ngResource']);

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
        .when('/accounts',{
            templateUrl:'template/accounts.html',
            controller:'accountsController'
        })
        .otherwise({
            redirectTo:'/'
        });
});

bankApp.factory('Transaction', function($resource) {
    return $resource('transactions');
});

bankApp.factory('Person', function($resource) {
    return $resource('persons');
});

bankApp.factory('Account', function($resource) {
    return $resource('persons/:personId/accounts', {});
});

bankApp.controller('mainController', function($scope) {

});

bankApp.controller('personsController', function($scope, Person) {
    $scope.persons = Person.query();
});

bankApp.controller('transactionsController', function($scope, Transaction) {
    $scope.transactions = Transaction.query();
});

bankApp.controller('accountsController', function($scope, Account) {
    $scope.accounts = Account.query({personId: 100002});
});