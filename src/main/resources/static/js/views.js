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
        })

    }

});