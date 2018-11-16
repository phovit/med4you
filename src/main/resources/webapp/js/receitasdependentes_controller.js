'use strict'

var app = angular.module('receitasdependentes', []);

app.controller('receitasdependentescontroller', function ($scope, $http) {
    console.log('receitasdependentescontroller');

    $scope.getReminders = function () {
        $http({
            method: 'GET',
            url: '/users/logged'
        }).then(function (response) {
            $scope.user = response.data;


            $http({
                method: 'GET',
                url: '/users/getDependente'
            }).then(function (response) {
                $scope.dependente = response.data;
            });


            $http({
                method: 'GET',
                url: '/medicalPrescriptions/findByResponsableUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.medicalPrescriptions = response.data;
            });
        });
    };

    $scope.getReminders();

});
