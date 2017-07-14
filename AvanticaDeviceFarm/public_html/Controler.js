var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope, $http) {
    $scope.CurrentItem = "";
    $scope.totalTime = 0;
    $scope.user = "";
    $scope.DeviceList = [
    ];
    $scope.load = function () {
        $http.get('http://localhost:8080/device').
                then(function (response) {
                    console.log(response.data);
                    $scope.DeviceList = response.data;
                });
    };
    $scope.load();
    $scope.login = function () {


         $.ajax({url: 'http://localhost:8080/login',
              type: 'GET',
            data: { username: document.getElementById("user").value,
                password : document.getElementById("pass").value} ,
            success: function(result){
             $scope.user = result;
             console.log("Succes");$scope.$apply();
        },
                error: function(result) {
                    alert("Data not found");
                }
    });
       


    };
    $scope.logout = function () {
          $scope.user = "";
    };
    
    $scope.AsigMe = function () {

        var parameter = JSON.stringify({name: "user", time: 1, priority: "high"});
        $http.post('http://localhost:8080/request', parameter).
                then(function (data) {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(data);
                    if (data.status === 200) {
                        alert("Request Succesfull");
                        $scope.load();
                        $scope.close();
                    }
                }, function (error) {
                    alert("Request Failed");
                });

    };
    $scope.expand = function (index) {

        $scope.CurrentItem = $scope.DeviceList[index];
        $scope.totalTime = 0;
        angular.forEach($scope.CurrentItem.stack, function (item) {

            $scope.totalTime = $scope.totalTime + item.time;
        });

        document.getElementById('myModal').style.display = "block";

    };
    $scope.close = function () {
        document.getElementById('myModal').style.display = "none";
    };
    $scope.whatClassIsIt = function (someValue) {

        if (someValue === "Bussy")
            return "BG_Green";
        else
            return "BG_Blue";

    };

    $scope.checkStatus = function (status) {
        if (status === "Bussy") {
            return true;
        }
        return false;
    };

    onDocumentClick = function (event) {

        // check for flag
        if (!$scope.closeFlag) {
            $scope.closeFlag = true;
            return;
        }
        // code to hide the div
    };



});
app.filter('priorityOrder', function () {
    function CustomOrder(item) {
        switch (item) {
            case 'High':
                return 1;

            case 'Medium':
                return 2;

            case 'Low':
                return 3;
        }
    }
    return function (items, field) {
        var filtered = [];
        angular.forEach(items, function (item) {
            filtered.push(item);
        });
        filtered.sort(function (a, b) {
            return (CustomOrder(a.priority) > CustomOrder(b.priority) ? 1 : -1);
        });
        return filtered;
    };
});
window.onclick = function (event) {
    var modal = document.getElementById('myModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }

};
