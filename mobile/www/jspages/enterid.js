$(document).ready(function() {
    $("#enter-id-continue").click(function() {
        AppSettings.setUserID($("#enter-id-id").val());   
        changePage("main-page");
    });
});