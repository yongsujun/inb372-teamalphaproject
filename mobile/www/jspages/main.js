function makeCode() {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 5; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function messageNotification(from, message) {
    return "<li class='alert alert-warning message-notification' from='" + from + "' message='" + message + "' not-code='" + makeCode() +"'><strong>New Message!</strong> from " + from + "</li>";
}

function medicalNotification() {
    return "<a><li class='alert alert-info medical-notification'><strong>Medical Alert!</strong></li></a>";
}

$(document).on("click", ".message-notification", function() {
    $("#main-page-message-popup").attr("not-code", $(this).attr("not-code"));
    $("#main-page-message-popup-from").html("<strong>Message From: </strong>" + $(this).attr("from"));
    $("#main-page-message-popup-msg").html($(this).attr("message"));
    $("#main-page-message-popup-remove").prop("checked", false);
    $("#main-page-message-popup-reply").val("");
    $("#main-page-message-popup").popup("open", null); 
});

$(document).on("click", "#main-page-message-popup-close", function() {
    var code = $("#main-page-message-popup").attr("not-code");
    
    if ($("#main-page-message-popup-remove").is(":checked")) {
        $("[not-code='" + code + "']")[0].remove();
    }
    
    $("#main-page-message-popup").popup("close", null);
});

$(document).ready(function() {
 
    
    $("#main-page-settings").click(function() {
        $("#settings-page-id").val(AppSettings.getUserID());
        $("#settings-page-id-feedback").css("display", "none");
        changePage("settings-page");
    });
    
    $("#main-page-send-alert").click(function() {
        
        intel.xdk.player.startAudio("audio/message-alert.mp3",false);  
        
        $("#main-page-notification-list").prepend(messageNotification("Andrew", "Please visit Dr Tim"));
    });
    
    $("#mappopupbtn").click(function() {
  
    });
});