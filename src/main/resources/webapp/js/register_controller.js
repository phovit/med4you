'use strict'

var app = angular.module('register', []);

app.controller('registercontroller', function ($scope, $http) {
    console.log('registercontroller');
    $scope.oldUser={};

    $scope.forcaSenha=0;

    $scope.changedUser = function(){
        return $scope.oldUser.address !== $scope.user.address ||
        $scope.oldUser.birthDate !== $scope.user.birthDate ||
        $scope.oldUser.cellPhone !== $scope.user.cellPhone ||
        $scope.oldUser.cpf !== $scope.user.cpf ||
        $scope.oldUser.email !== $scope.user.email ||
        $scope.oldUser.id !== $scope.user.id ||
        $scope.oldUser.identity !== $scope.user.identity ||
        $scope.oldUser.name !== $scope.user.name ||
        $scope.oldUser.phone !== $scope.user.phone ||
        $scope.oldUser.username !== $scope.user.username
    }


    $scope.cancel = function () {
        $scope.getInfoUser();
    };

    $scope.save = function () {
        if ($scope.user.password !== $scope.user.confirmPassword && !!$scope.changePassword) {
            alert('A senha não coincide com a confirmação de senha');
        
        } else {
            $scope.sendPhoto();

            $http({
                method: 'POST',
                url: '/users',
                data: $scope.user
            }).then(function (response) {
                console.log(response);
                alert('Registrado com sucesso');
            }, function (error) {
                alert('Erro ao Cadastrar');
            });
        }
    }


    $scope.getStyle = function () {
        var forca = verificaForca();
        if (forca < 30) {
            return {'background-color': 'red', 'width': forca};
        } else if (forca < 60) {
            return {'background-color': 'yellow', 'width': forca};
        } else if (forca < 85) {
            return {'background-color': 'blue', 'width': forca};
        } else {
            return {'background-color': 'green', 'width': forca};
        }
    }




    function verificaForca() {
        if( !$scope.user || !$scope.user.password ){
            return 0;
        }
        var senha = $scope.user.password;


        var forca = 0;

        var regLetrasMa     = /[A-Z]/;
        var regLetrasMi     = /[a-z]/;
        var regNumero       = /[0-9]/;
        var regEspecial     = /[!@#$%&*?]/;

        var tam         = false;
        var tamM        = false;
        var letrasMa    = false;
        var letrasMi    = false;
        var numero      = false;
        var especial    = false;

        if(senha.length >= 6) tam = true;
        if(senha.length >= 10) tamM = true;
        if(regLetrasMa.exec(senha)) letrasMa = true;
        if(regLetrasMi.exec(senha)) letrasMi = true;
        if(regNumero.exec(senha)) numero = true;
        if(regEspecial.exec(senha)) especial = true;

        if(tam) forca += 10;
        if(tamM) forca += 10;
        if(letrasMa) forca += 10;
        if(letrasMi) forca += 10;
        if(letrasMa && letrasMi) forca += 20;
        if(numero) forca += 20;
        if(especial) forca += 20;

        $scope.forcaSenha = forca;

        //Map#9jop2a
        return forca;
    }

    function validaSenha() {
        var senha = $scope.user.password;
        if ( !senha ){
            senha = '';
        }
        var regex = /^(?=(?:.*?[A-Z]){3})(?=(?:.*?[0-9]){2})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%;*(){}_+^&]*$/;

        if (senha.length < 8) {
            alert("A senha deve conter no minímo 8 digitos!");
            return false;
        }
        else if (!regex.exec(senha)) {
            alert("A senha deve conter no mínimo 1 caractere maiúsculo, 1 número e 1 caractere especial!");
            return false;
        }
        return true;
    }

    $scope.sendPhoto = function () {
        $http({
            method: 'POST',
            url: '/files/upload',
            data: $scope.image,
            transformRequest: angular.identity,
            headers: {enctype:'multipart/form-data',
                              'Content-Type': undefined
                    }
        }).then(function (response) {
            console.log(response);
            alert('upload ok -->'+response.data);
        }, function (error) {
            alert('Erro ao enviar imagem');
        });
    };

});