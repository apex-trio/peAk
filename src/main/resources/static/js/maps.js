'use strict';

var map;
function initMap() {
    console.log("in maps.js")
    var myLatlng = new google.maps.LatLng(-34.397, 150.644);
    //var myLatlng = new google.maps.LatLng(lat, long);


        var mapOptions = {
          zoom: 9,
          center: myLatlng,
          mapTypeId: 'terrain'
        };

        map = new google.maps.Map(document.getElementById('map'), mapOptions);
}