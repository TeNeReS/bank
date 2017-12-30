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
        .when('/persons/:id/accounts',{
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
    return $resource('persons/:id/accounts', {});
});

bankApp.controller('mainController', function($scope) {

});

bankApp.controller('personsController', function($scope, Person) {
    $scope.persons = Person.query();
    $scope.editPerson = {};
    $scope.submit = function() {
        var newPerson = $scope.editPerson;
        Person.save(newPerson)
            .$promise.then(function(personFromServer) {
            $scope.persons.push(personFromServer);
        });
        $scope.editPerson = {};
    };
});

bankApp.controller('transactionsController', function($scope, Transaction) {
    $scope.transactions = Transaction.query();
});

bankApp.controller('accountsController', function($scope, $route, Account) {
    $scope.accounts = Account.query({id: $route.current.params.id});
    $scope.editAccount = {};
    $scope.submit = function() {
        var newAccount = $scope.editAccount;
        Account.save({id: $route.current.params.id}, newAccount)
            .$promise.then(function(accountFromServer) {
            $scope.accounts.push(accountFromServer);
        });
        $scope.editAccount = {};
    };
});