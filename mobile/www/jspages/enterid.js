$(document).ready(function() {
    
    $("#enter-id-continue").click(function() {
        var patientID = $("#enter-id-id").val();
        
        $("#enter-id-load-div").css("visibility", "visible");
        $("#enter-id-id").attr("disabled", "disabled");
        $("#enter-id-continue").attr("disabled", "disabled");
        
        AppEngineEndpoints.getPatient(patientID, function(response) {
           
            // some work around code for issue 44
            if ((typeof response.code != "undefined") && (typeof response.error == "object")) {
                //patient id is valid
                $("#enter-id-load-div").css("visibility", "hidden");
                $("#enter-id-id").removeAttr("disabled");
                $("#enter-id-continue").removeAttr("disabled");
                alert("Error patient ID is not valid");
            } else {
                AppSettings.setUserID(patientID);
                AppSettings.setPatientInfo(response);
                $("#enter-id-id").val("");
                $("#enter-id-load-div").css("visibility", "hidden");
                $("#enter-id-id").removeAttr("disabled");
                $("#enter-id-continue").removeAttr("disabled");                
                changePage("main-page");
            }
        });
    });
});