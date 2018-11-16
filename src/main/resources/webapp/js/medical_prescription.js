'use strict'

var app = angular.module('medicalprescription', []);

app.controller('medicalprescriptioncontroller', function ($scope, $http,$state) {
    $scope.medicalPrescriptions = [];

    $scope.getmedicalPrescriptions = function () {
        $http({
            method: 'GET',
            url: '/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $http({
                method: 'GET',
                url: '/medicalPrescriptions/findByUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.medicalPrescriptions = response.data;
            });
        });

    };

    $scope.delete = function (id) {
        $http({
            method: 'DELETE',
            url: '/medicalPrescriptions/' + id
        }).then(function (response) {
            $scope.medicalPrescriptions = response.data;
        });

    };


    $scope.save = function (medicalPrescription) {
        medicalPrescription.user = $scope.user;
            $http({
                method: 'POST',
                url: '/medicalPrescriptions',
                data: medicalPrescription
            }).then(function (response) {
                $scope.getmedicalPrescriptions();
                $scope.clean();
            }, function (error) {
                console.log(error);
            });
        
    }
    $scope.edit = function (medicalPrescription) {
        $scope.medicalPrescription = angular.copy(medicalPrescription);
        $scope.doctorSearch = angular.copy(medicalPrescription.doctor.name);
    }
    $scope.cadastrarMedico = function () {
        $state.go("doctor")
    }

    $scope.getmedicalPrescriptions();

    $scope.cancel = function () {
        $scope.getmedicalPrescriptions();
        $scope.isEditing = false;
    };
    $scope.clean = function () {
        $scope.medicalPrescription = {};
        $scope.doctorSearch = "";
    }

    $scope.findDoctors = function () {
        if ($scope.doctorSearch.length >= 3) {
            $http({
                method: 'GET',
                url: '/doctors/findByName/' + $scope.doctorSearch
            }).then(function (response) {
                $scope.doctorSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    }

    $scope.selectDoctor = function (doctor) {
        if (!$scope.medicalPrescription) {
            $scope.medicalPrescription = {};
        }
        $scope.doctorSearch = doctor.name;
        $scope.medicalPrescription.doctor = doctor;
        $scope.isMedSelected = true;
        $scope.showMedResults = false;
    }
});