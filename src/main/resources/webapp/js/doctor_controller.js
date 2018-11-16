'use strict'

var app = angular.module('doctor', []);

app.controller('doctorcontroller', function ($scope, $http) {



    $scope.clear = function () {
        $scope.doctor = null;
    }
    $scope.save = function () {
        $http({
            method: 'POST',
            url: '/doctors',
            data: $scope.doctor
        }).then(function (response) {
            console.log('Médico cadastrado com sucesso');
            console.log(response);
            $scope.findAll();
        }, function (error) {
            console.log('Erro ao cadastrar médico');
        });
    }

    $scope.delete = function (id) {
        $http({
            method: 'DELETE',
            url: '/doctors/' + id
        }).then(function (response) {
            console.log(response);
            $scope.findAll();
        });

    };

    $scope.findAll = function () {
        $http({
            method: 'GET',
            url: '/doctors'
        }).then(function (response) {
            $scope.doctors = response.data;
        });

    };
    $scope.edit = function (doc) {
        $scope.doctor = angular.copy(doc);
    }
    $scope.findAll();

});