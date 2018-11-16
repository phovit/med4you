'use strict'

var app = angular.module('lembretesdependentes', []);

app.controller('lembretesdependentescontroller', function ($scope, $http) {
    console.log('lembretesdependentescontroller');

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
                url: '/reminders/findByResponsableUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.reminders = response.data;
            });
        });
    };

    $scope.getReminders();

});

