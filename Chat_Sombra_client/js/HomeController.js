
app.controller('HomeController', ['$scope','$http','$window', function ($scope, $http,$window) {
    $scope.currentUser=null;

    $http({
        method: 'GET',
        url:'http://localhost:8080/identifyUser',
        withCredentials: true
    }).success(function (data) {
        $scope.currentUser=data;
    });

    $scope.logout=function () {
        localStorage.setItem('currentUser',null);
        $http({
            method: 'GET',
            url:'http://localhost:8080/logout',
            withCredentials: true
        });
        $window.location.href = 'http://localhost:63343/Chat_Sombra_client/index.html?_ijt=o7j01j1d17tlr0292eq890j6gq#/';
        $scope.reloadPage();
    };

    $scope.reloadPage = function(){$window.location.reload();};

}]);