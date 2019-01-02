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
            modMap(resort.lat, resort.long);

            $('.ots-widget > iframe').attr('src', 'https://www.onthesnow.com/widget/snow?resort=' + resort.widgetId + '&color=b');
            $('.ots-widget > p > a').attr('href', resort.otsUrl);
            $('#teams').empty();
            resort.teams.forEach(team => {
                let div = $('<div class="team_card" data-id="' + team.id + '"></div>');
                div.append('<span>' + team.difficulty + ' </span>');
                div.append('<span>' + team.name + ' </span>');
                div.append('<span>' + team.users.length + '/' + team.capacity + ' </span>');
                div.append('<span>' + team.description + ' </span>');
                $('#teams').append(div);
            });

        })

    }

    function modMap() {
    var map;
        
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