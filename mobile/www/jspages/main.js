$(document).ready(function() {
    $("#main-page-settings").click(function() {
        $("#settings-page-id").val(AppSettings.getUserID());
        $("#settings-page-id-feedback").css("display", "none");
        changePage("settings-page");
    });
    
    $("#mappopupbtn").click(function() {
            console.log(LocationService.getLatitude());
            console.log(LocationService.getLongitude());
            var myLatlng = new google.maps.LatLng( 51.520838, -0.140261 );
            var myOptions = {
                zoom: 15,
                center: myLatlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            var map = new google.maps.Map( document.getElementById( "map_canvas" ), myOptions );  
    });
});