$(document).ready(function() {
    $("#main-page-settings").click(function() {
        $("#settings-page-id").val(AppSettings.getUserID());
        $("#settings-page-id-feedback").css("display", "none");
        changePage("settings-page");
    });
});