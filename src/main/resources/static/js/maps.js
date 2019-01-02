'use strict';

var map;
function initMap() {
    console.log("in maps.js")
    var myLatlng = new google.maps.LatLng(46.92332964, -121.475998096);
    //var myLatlng = new google.maps.LatLng(lat, long);


        var mapOptions = {
          zoom: 9,
          center: myLatlng,
          mapTypeId: 'terrain'
        };

      var location = {lat: 46.92332964, lng: -121.475998096};

        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        var marker = new google.maps.Marker({position: location, map: map});
}