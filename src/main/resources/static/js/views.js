'use strict'

$(document).ready(() => {

//    $('#logo_frame').on('click', () => {
//        alert('jq works!')
//    });

    $('.resort_button_div').on('click', getResortSelection);

    function getResortSelection(e){

        e.stopPropagation();
//        console.log($(this)[0].dataset.id);
        $.ajax({
           url:"/resorts/" + $(this)[0].dataset.id,
           type:"GET"
        }).done(resort => {
            console.log(resort.name);
            $('#ajax_test').text(resort.name);
            modMap(resort.lat, resort.long);
        })

    }

    var map;
    function modMap(lat, long) {
    var myLatlng = new google.maps.LatLng(lat, long);

    var mapOptions = {
      zoom: 8,
      center: myLatlng,
      mapTypeId: 'terrain'
    };

    map = new google.maps.Map(document.getElementById('map'),
     mapOptions);
    }

});