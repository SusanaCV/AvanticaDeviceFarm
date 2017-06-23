var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope) {
    $scope.DeviceSrc = "";
    $scope.Stack = [];//name,hours,priority
    $scope.feactures = [];
    $scope.name = "";
    $scope.totalTime = 0;
    $scope.DeviceList = [
        {name: "Samsumg S7", currentUser: "Keneth", status: "In use by ", src: "Smartphone.jpg",
            stack: [
                {name: "jimmy", time: 2, priority: "high"},
                {name: "susana", time: 5, priority: "low"},
                {name: "jimmy", time: 2, priority: "medium"}],
            feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "Samsumg S5", status: "Available", src: "Smartphone.jpg", stack: [{name: "jimmy", time: 2, priority: "high"}], feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "Samsumg note", status: "Bussy", src: "Smartphone.jpg", stack: [{name: "jimmy", time: 2, priority: "high"}], feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "Samsumg", status: "Bussy", src: "Smartphone.jpg", stack: [{name: "jimmy", time: 2, priority: "high"}], feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}}

    ];

    $scope.expand = function (index, item) {
        var element = document.getElementById("element" + index);
        if (item.status === "Available") {

            if (element.style.width === "550px") {
                element.style.width = "200px";
                return;
            }
            element.style.maxWidth = "100%";
            element.style.width = "550px";
        } else {

            $scope.name = $scope.DeviceList[index].name;
            $scope.DeviceSrc = $scope.DeviceList[index].src;
            $scope.Stack = $scope.DeviceList[index].stack;
            $scope.feactures = $scope.DeviceList[index].feactures;
            $scope.totalTime = 0;
            for (var item in $scope.Stack) {
                $scope.totalTime = $scope.totalTime + $scope.Stack[item].time;
            }

            var modal = document.getElementById('myModal');
            modal.style.display = "block";
        }
    };
    $scope.close = function () {
        document.getElementById('myModal').style.display = "none";
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
            case 'high':
                return 1;

            case 'medium':
                return 2;

            case 'low':
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
