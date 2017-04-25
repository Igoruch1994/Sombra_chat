
app.controller('RegistrationController', ['$scope','$http','$window','$timeout', function ($scope, $http,$window,$timeout) {
    $scope.user = {};

    $scope.registration=function () {
        $scope.user.login=$scope.login;
        $scope.user.password=$scope.password;
        $scope.user.email=$scope.email;
        $scope.user.phone=$scope.phone;
        $http.post('http://localhost:8080/registration', $scope.user)
            .success(function (data, status, headers, config) {
                $scope.RegistrationData=data;
                if ($scope.RegistrationData==="Success registration!"){
                    $timeout(function(){$window.location="#/login";}, 1000);
                }
            })
    };
}]);