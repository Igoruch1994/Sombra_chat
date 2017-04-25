
app.controller('chatController', ['$scope','$http','$window', function ($scope, $http,$window) {

    $scope.infoButtonNumber=3;
    $scope.InfoText='All Users';

    $scope.GetConversationOfCurrentUser=function () {
        {
            $http({
                method: 'GET',
                url:'http://localhost:8080/conversation',
                withCredentials: true
            }).success(function (data) {
                $scope.CurrentUserConversation=data;
                $scope.GetAllUsers(3);
            });
        }
    };

    $scope.createNewConversation = function (Name) {
        $http({
            method: 'POST',
            url:'http://localhost:8080/conversation' ,
            withCredentials: true,
            data: {name:Name}
        }).success(function (data) {
            $scope.createConversation=false;
            $window.alert("Conversation " + Name+" create!");
            $scope.GetConversationOfCurrentUser();
        })
    };

    $scope.getMessages= function (conve,name) {
        $scope.conveId=conve;
        $scope.ConversationName=name;
        $scope.GetUsersInConversations();
        $http({
            method: 'GET',
            url: 'http://localhost:8080/message/conversation/' + conve,
            withCredentials: true
        }).success(function (data) {
            $scope.messages = data;
        });
    };

    $scope.GetUsersInConversations=function () {
        {
            $http({
                method: 'GET',
                url:'http://localhost:8080/user/conversation/'+$scope.conveId ,
                withCredentials: true
            }).success(function (data) {
                $scope.UserInConversation=data;

            });
        }
    };

    $scope.SendEmail=function () {
        {
            $http({
                method: 'POST',
                url:'http://localhost:8080/message/email/conversation/'+$scope.conveId,
                withCredentials: true,
                data: {
                    text: $scope.textMessage
                }
            }).success(function (data) {
            });
        }
    };

    $scope.sendMessages = function () {
        if ($scope.textMessage!=null) {
            $http({
                method: 'POST',
                url:'http://localhost:8080/message/conversation/'+$scope.conveId,
                withCredentials: true,
                data: {
                    text: $scope.textMessage
                }
            }).success(function (data) {
                $scope.getMessages($scope.conveId);
                $scope.SendEmail();
                $scope.textMessage = '';
            })
        }
    };

    $scope.GetAllUsers=function (numb) {
        {
            $scope.infoButtonNumber=numb;
            if ($scope.infoButtonNumber==3) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/user',
                    withCredentials: true
                }).success(function (data) {
                    $scope.allUsers = data;
                });
            }
        }
    };

    $scope.GetAllFriends=function () {
        {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/user/friend',
                withCredentials: true
            }).success(function (data) {
                $scope.userFriends=data;
            });
        }
    };

    $scope.AddToFriend=function (friendId) {
        {
            $http({
                method: 'GET',
                url:'http://localhost:8080/user/friend/'+ friendId ,
                withCredentials: true
            }).success(function (data) {
                if (data) {
                    $window.alert("Add new friend!");
                }
            });
        }
    };

    $scope.AddToChat=function (friendId) {
        {
            $http({
                method: 'GET',
                url:'http://localhost:8080/conversation/user/'+$scope.conveId+'/'+ friendId ,
                withCredentials: true
            }).success(function (data) {
                $scope.GetUsersInConversations();
                $window.alert("Add to chat!");
            });
        }
    };

}]);
