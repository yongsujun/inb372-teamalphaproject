function updateLocation() {
    var coords = getLocation();
    console.log(coordinates);
    $("#main-page-footer-location").text(coordinates[0] + " " + coordinates[1]);
}

$(document).ready(function() {
    
    
    window.setInterval(updateLocation, 2000);
    //console.log("passed getLocation()");
    
    function changePage(page) {
        $(":mobile-pagecontainer").pagecontainer("change", "#" + page, { reverse: false});     
    }
    
    if (getID() === null) {
        changePage("enter-id");
    } else {
        changePage("main-page");
    }
    
    $("#enter-id-continue").click(function() {
        setID($("#enter-id-id").val());   
    });
    
    
    $("#main-page-settings").click(function() {
        $("#settings-page-id").val(getID());
        $("#settings-page-id-feedback").css("display", "none");
        changePage("settings-page");
    });
    
    $("#settings-page-save").click(function() {
        var newID = $("#settings-page-id").val();
        
        if (newID == "") {
            $("#settings-page-id-feedback").text("   (ID must not be empty)");
            $("#settings-page-id-feedback").css("display", "inline");
        } else {
            setID(newID);   
            changePage("main-page");
        }
    });
    
});