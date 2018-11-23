'use strict'

var app = angular.module('medicineedit', []);

app.controller('medicineeditcontroller', function ($scope, $http, $state, $stateParams) {


    $scope.find = function (medicineId) {
        $http({
            method: 'GET',
            url: '/medicine/' + medicineId
        }).then(function (response) {
            $scope.medicine = response.data;
            $scope.image = $scope.medicine.image;

            angular.element(document.querySelector('#imageField')).css({
                'background-image': 'url("data:image/png;base64,' + $scope.image  + '")',
                'background-repeat': 'no-repeat',
                'background-size': '100% 100%'
            });
        }, function (error) {
            alert("Medicamento não encontrado com o ID: " + medicineId);
            $state.go("medicineregister");
        });

    }
    $scope.$on('changedImage', function(ev, args) {
            $scope.image = args;
    });
    $scope.find($stateParams.id);

    $scope.clear = function () {
        $scope.medicine = null;
    }
    $scope.save = function () {

        $scope.medicine.image = $scope.image;
        $http({
            method: 'POST',
            url: '/medicine',
            data: $scope.medicine
        }).then(function (response) {
            console.log(response);
            $state.go("medicine");
        }, function (error) {
            alert('Erro ao cadastrar medicamento');
        });

    }

})
.service("uploadService", function ($http, $q) {

    return ({
        upload: upload
    });

    function upload(file) {
        var upl = $http({
            method: 'POST',
            url: 'http://jsonplaceholder.typicode.com/posts', // /api/upload
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            data: {
                upload: file
            },
            transformRequest: function (data, headersGetter) {
                var formData = new FormData();
                angular.forEach(data, function (value, key) {
                    formData.append(key, value);
                });

                var headers = headersGetter();
                delete headers['Content-Type'];

                return formData;
            }
        });
        return upl.then(handleSuccess, handleError);

    }

    function handleError(response, data) {
        if (!angular.isObject(response.data) || !response.data.message) {
            return ($q.reject("An unknown error occurred."));
        }

        return ($q.reject(response.data.message));
    }

    function handleSuccess(response) {
        return (response);
    }

    })
    .directive("fileinput2", [function () {
        return {
            scope: {
                fileinput2: "=",
                filepreview: "="
            },
            link: function (scope, element, attributes) {
                element.bind("change", function (changeEvent) {
                    scope.fileinput2 = changeEvent.target.files[0];
                    var reader = new FileReader();
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            scope.filepreview = loadEvent.target.result;
                            if(!scope.$parent.$parent.medicine){
                                scope.$parent.$parent.medicine = {};
                            }
                            scope.$parent.$parent.medicine.image = loadEvent.target.result.substring(loadEvent.target.result.indexOf('base64,')+7);
                            scope.$emit('changedImage', scope.$parent.$parent.medicine.image);
                            angular.element(document.querySelector('#imageField')).css({
                                'background-image': 'url("data:image/png;base64,' + scope.$parent.$parent.medicine.image  + '")',
                                'background-repeat': 'no-repeat',
                                'background-size': '100% 100%'
                            });

                        });
                    }
                    reader.readAsDataURL(scope.fileinput2);
                });
            }
        }
    }]);