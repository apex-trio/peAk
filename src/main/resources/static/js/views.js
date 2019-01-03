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
            console.log(resort);
            $('#ajax_test').text(resort.name);

            // inserting modMap function to change map center point via map input
//            let latty = resort.lat;
//            let longy = resort.long;

            console.log("This is our resort latitude", resort.latitude);
            console.log("This is our resort longitude", resort.longitude);

            localStorage.setItem('storedLat', JSON.stringify(resort.latitude));
            localStorage.setItem('storedLong', JSON.stringify(resort.longitude));

            modMap();

            $('.ots-widget > iframe').attr('src', 'https://www.onthesnow.com/widget/snow?resort=' + resort.widgetId + '&color=b');
            $('.ots-widget > p > a').attr('href', resort.otsUrl);
            if ($('#create').length) {
                $('#teams').empty().append('<a href="/newteam?resortId=' + resort.id + '">Create a Team</a>');
            } else {
                $('#teams').empty();
            }
            resort.teams.forEach(team => {
                let div = $('<div class="team_card" data-id="' + team.id + '"></div>');
                div.append('<span>' + team.difficulty + ' </span>');
                div.append('<span>' + team.name + ' </span>');
                div.append('<span>' + team.users.length + '/' + team.capacity + ' </span>');
                div.append('<span>' + team.description + ' </span>');
                div.append('<a href="/teams/' + team.id + '">View</a>')
                $('#teams').append(div);

            });
            
            $('body').append('<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxgddLplSZhq5H2eEIxCPdacE-VmAWHk0&callback=modMap" async defer></script>');


        })

    }

    console.log("in views.js");
    var map;

    function modMap() {

        var resortLat = JSON.parse(localStorage.getItem('storedLat'));
        var resortLong = JSON.parse(localStorage.getItem('storedLong'));
        var myLatlng = new google.maps.LatLng(resortLat, resortLong);

        var mapOptions = {
          zoom: 9,
          center: myLatlng,
          mapTypeId: 'terrain'
        };

        var location = {lat: resortLat, lng: resortLong};

        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        var marker = new google.maps.Marker({position: location, map: map});

    }

});