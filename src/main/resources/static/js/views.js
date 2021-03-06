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

  
            $('#report_container > iframe').attr('src', 'https://www.onthesnow.com/widget/snow?resort=' + resort.widgetId + '&color=b');
            $('#report_container > p > a').attr('href', resort.otsUrl);
            $('#teams').empty();

            $('.ots-widget > iframe').attr('src', 'https://www.onthesnow.com/widget/snow?resort=' + resort.widgetId + '&color=b');
            $('.ots-widget > p > a').attr('href', resort.otsUrl);

                $('#teams').empty();


            resort.teams.forEach(team => {
                let div = $('<div class="team_card" data-id="' + team.id + '"></div>');
                div.append('<span>' + team.difficulty + ' </span><br>');
                div.append('<span>' + team.name + ' </span><br>');
                div.append('<span>' + team.users.length + '/' + team.capacity + ' </span><br>');
                div.append('<span>' + team.description + ' </span><br>');
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


    //////////


    $('#mobile_nav_button_left').on('click', () => {
                $( "#vertical_locations_m" ).slideToggle();
            });

    $('.resort_button_div').on('click', () => {
        $( "#vertical_locations_m" ).hide(0);
    });

    $('#vertical_locations_m').on('mouseleave', () => {
            $( "#vertical_locations_m" ).slideUp();
        });

    ///

    $('#mobile_nav_button_right').on('click', () => {
                    $("#nav_m").slideToggle();
                });

//    $('#nav_m').on('mouseleave', () => {
//                $( "#nav_m" ).slideUp();
//            });





});