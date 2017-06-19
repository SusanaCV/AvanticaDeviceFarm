var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope) {
    $scope.DeviceList = [
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}},
        {name: "susana", status: "Available", src: "Smartphone.jpg", feactures: {modelo: "samsum", andoid: "4.3", tamaño: "10"}}
    ];

    $scope.expand = function (index, item) {
        var element = document.getElementById("element"+index);
        if (item.status === "Available") {
            console.log(element);
            if (element.style.width === "100%") {
                element.style.width = "200px";
                element.style.overflow = "hidden";
                return;
            }
            element.style.maxWidth = "100%";
            element.style.width = "100%";
            element.style.overflow = "scroll";
        } else {

        }
    }
});
