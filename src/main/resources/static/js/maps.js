'use strict';

var map;
function initMap() {
var myLatlng = new google.maps.LatLng(-34.397, 150.644);
//var myLatlng = new google.maps.LatLng(lat, long);


var mapOptions = {
  zoom: 8,
  center: myLatlng,
  mapTypeId: 'terrain'
};

map = new google.maps.Map(document.getElementById('map'),
 mapOptions);
}