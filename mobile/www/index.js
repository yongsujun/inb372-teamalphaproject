function updateLocation() {
     LocationService.updateLocation();
    $("#main-page-footer-location").text(LocationService.getLatitude() + " " + LocationService.getLongitude());
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