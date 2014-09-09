function updateLocation() { 
    LocationService.updateLocation();
    
    var latitude = LocationService.getLatitude();
    var longitude = LocationService.getLongitude();
    

    var geocoder = new google.maps.Geocoder();
    var latLng = new google.maps.LatLng(latitude, longitude);
    
    
    AppEngineEndpoints.updateLocation(AppSettings.getUserID(), latitude, longitude);
    
    geocoder.geocode( { 'latLng': latLng}, function(results, status) {

        if (status == google.maps.GeocoderStatus.OK) {
            if (results[1]) {
            
                $("#main-page-footer-location").text(results[1].address_components[1].short_name + ", " + results[1].address_components[2].short_name );
                
            }      
        }
    });
}
                                 


function changePage(page) {
    $(":mobile-pagecontainer").pagecontainer("change", "#" + page, { reverse: false});     
}

$(document).ready(function() {
    
    
    window.setInterval(updateLocation, 2000);

    if (!AppSettings.hasUserID()) {
        changePage("enter-id");
    } else {
        changePage("main-page");
    }
    
    
});

