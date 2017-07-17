var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope, $window, $http) {
    $scope.CurrentItem = "";
    $scope.totalTime = 0;
    $scope.user = "";
    $scope.id = 0;
    $scope.DeviceList = [
    ];
    $scope.load = function () {
        $http.get('http://localhost:8080/device').
                then(function (response) {
                    console.log(response.data);
                    $scope.DeviceList = response.data;
                });
    };
    $scope.login = function () {


        $.ajax({url: 'http://localhost:8080/login',
            type: 'GET',
            data: {username: document.getElementById("user").value,
                password: document.getElementById("pass").value},
            success: function (result) {
                $scope.user = result.name;
                $scope.id = result.id;
                $scope.Permission = result.permision;
                console.log(result);
                $scope.load();
                $scope.$apply();
            },
            error: function (result) {
                alert("Data not found");
            }
        });



    };
    $scope.logout = function () {
        $scope.user = "";
    };

    $scope.AsigMe = function () {

        $.ajax({url: 'http://localhost:8080/request',
            type: 'POST',
            data: {
                idUser: $scope.id,
                idDevice: $scope.CurrentItem.id,
                time: document.getElementById("requestTime").value,
                priority: document.getElementById("requestPriority").value
            },
            success: function (result) {
                alert("Request Succesfull");
                document.getElementById("requestTime").value = "";
                $scope.load();
                $scope.close();
            },
            error: function (result) {
                alert("Request Failed");
            }
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

    $scope.delete = function (index) {
        console.log($scope.DeviceList[index].id);

        $.ajax({url: 'http://localhost:8080/delete',
            type: 'POST',
            data: {id: $scope.DeviceList[index].id},
            success: function (result) {
                $scope.load();
            },
            error: function (result) {
                alert("delete fail");
            }
        });
    };
$scope.free = function (index) {

        $.ajax({url: 'http://localhost:8080/free',
            type: 'POST',
            data: {id: $scope.DeviceList[index].id},
            success: function (result) {
                $scope.load();
            },
            error: function (result) {
                alert("delete fail");
            }
        });
    };
    $scope.NewDevice = function () {

        $.ajax({url: 'http://localhost:8080/NewDevice',
            type: 'POST',
            data: {code: document.getElementById("code").value,
                img: $window.imagen,
                brand: document.getElementById("brand").value,
                model: document.getElementById("model").value,
                os: document.getElementById("os").value,
                version: document.getElementById("version").value,
                ip: document.getElementById("ip").value,
                mac: document.getElementById("mac").value},
            success: function (result) {
                document.getElementById("code").value = "";
                $window.imagen = "";
                document.getElementById("prev").src = "";
                document.getElementById("brand").value = "";
                document.getElementById("model").value = "";
                document.getElementById("os").value = "";
                document.getElementById("version").value = "";
                document.getElementById("ip").value = "";
                document.getElementById("mac").value = "";
                $scope.load();
            },
            error: function (result) {
                alert("New device not insert");
            }
        });


    };
    $scope.give = function (id){
        $.ajax({url: 'http://localhost:8080/give',
            type: 'POST',
            data: {
                id: id
            },
            success: function (result) {
                alert("Request Succesfull");
                $scope.load();
                $scope.close();
            },
            error: function (result) {
                alert("Request Failed");
            }
        });
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
var imagen;
function readFile() {

    if (this.files && this.files[0]) {

        var FR = new FileReader();

        FR.addEventListener("load", function (e) {
            console.log(e.target.result);
            imagen = e.target.result;
            document.getElementById("prev").src = imagen;
        });

        FR.readAsDataURL(this.files[0]);
    }

}