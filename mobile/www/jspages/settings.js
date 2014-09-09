$(document).ready(function() {
    $("#settings-page-save").click(function() {
        var newID = $("#settings-page-id").val();
        
        if (newID === "") {
            $("#settings-page-id-feedback").text("   (ID must not be empty)");
            $("#settings-page-id-feedback").css("display", "inline");
        } else {
            AppSettings.setUserID(newID);   
            changePage("main-page");
        }
    });
    
    $("#settings-page-reset").click(function() {
        AppSettings.reset(); 
        $("#settings-page-id").val("");
        changePage("enter-id");
    });
});