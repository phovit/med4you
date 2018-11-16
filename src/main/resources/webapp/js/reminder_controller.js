'use strict'

var app = angular.module('reminder', ['ngSanitize', 'ui.select']);

app.controller('remindercontroller', function ($scope, $http) {
    $scope.reminders = [];
    $scope.showAddForm = false;

    $scope.getReminders = function () {
        $http({
            method: 'GET',
            url: '/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $http({
                method: 'GET',
                url: '/reminders/findByUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.reminders = response.data;
            });
        });

    };

    $scope.delete = function (id) {
        $http({
            method: 'DELETE',
            url: '/reminders/' + id
        }).then(function (response) {
            $scope.reminders = response.data;
            $scope.getReminders();
        });

    };


    function saveAllReminders(reminder) {
        var qtdDoses =  parseInt($scope.qtdDoses);

        for (var i = 0; i < qtdDoses; i++) {
            reminder.onLoop = true;
            $scope.save(angular.copy(reminder));
            reminder.firstDose = angular.copy(addHours(reminder.firstDose, $scope.intervalo));
        }
    }

    function addHours(data, incremento) {
        return new Date(new Date(data).getTime() + 60 * 60 * incremento * 1000);
    }

    $scope.save = function (reminder) {
        reminder.medicine = $scope.$$childHead.reminder.medicine;
        reminder.user = $scope.user;
        var method = '';

        if (!$scope.isEditing) {
            method = 'POST';
        } else {
            method = 'PUT';
        }

        if (!!$scope.qtdDoses && $scope.qtdDoses > 1 && !!$scope.intervalo && !reminder.onLoop && method == 'POST') {
            saveAllReminders( reminder );
            return;
        }else{
            console.log(reminder.firstDose);
            $http({
                method: method,
                url: '/reminders',
                data: reminder
            }).then(function (response) {
                $scope.getReminders();
                $scope.clean();
                $scope.showAddForm = false;
                $scope.isEditing = false;
            }, function (error) {
                console.log(error);
            });
        }
    }
    $scope.edit = function (reminder) {
        $scope.isEditing = true;
        $scope.reminder = angular.copy(reminder);
        $scope.medicineSearch = angular.copy(reminder.medicine.name);
        $scope.showAddForm = true;
    }

    $scope.getReminders();

    $scope.cancel = function () {
        $scope.getReminders();
        $scope.isEditing = false;
    };
    $scope.clean = function () {
        $scope.reminder = {};
        $scope.medicineSearch = "";
    }

    $scope.findMedicines = function () {
        if ($scope.medicineSearch.length >= 3) {
            $http({
                method: 'GET',
                url: '/medicine/findByName/' + $scope.medicineSearch
            }).then(function (response) {
                $scope.medicineSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    }

    $scope.$watch('$$childHead.$select.search', function() {
        if (!!$scope.$$childHead.$select.search && $scope.$$childHead.$select.search.length >= 3) {
            $http({
                method: 'GET',
                url: '/medicine/findByName/' + $scope.$$childHead.$select.search
            }).then(function (response) {
                $scope.medicineSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    });

    $scope.findMedicines = function (value) {
        if (!!value && value.length >= 3) {
            $http({
                method: 'GET',
                url: '/medicine/findByName/' + value
            }).then(function (response) {
                $scope.medicineSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    }

    $scope.selectMedicine = function (med) {
        console.log("OnClick()");
        if (!$scope.reminder) {
            $scope.reminder = {};
        }
        $scope.medicineSearch = med.name;
        $scope.reminder.medicine = med;
        $scope.isMedSelected = true;
        $scope.showMedResults = false;
    }


    $scope.findAllMedicines = function () {
            $http({
                method: 'GET',
                url: '/medicine'
            }).then(function (response) {
                $scope.medicineSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
    }

    $scope.findAllMedicines();
});


app.filter('propsFilter', function() {
    return function(items, props) {
      var out = [];
  
      if (angular.isArray(items)) {
        var keys = Object.keys(props);
  
        items.forEach(function(item) {
          var itemMatches = false;
  
          for (var i = 0; i < keys.length; i++) {
            var prop = keys[i];
            var text = props[prop].toString().toLowerCase();
            if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
              itemMatches = true;
              break;
            }
          }
  
          if (itemMatches) {
            out.push(item);
          }
        });
      } else {
        // Let the output be the input untouched
        out = items;
      }
  
      return out;
    };
  });