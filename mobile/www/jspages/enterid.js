$(document).ready(function() {
    
    $("#enter-id-continue").click(function() {
        var patientID = $("#enter-id-id").val();
        
        AppEngineEndpoints.getPatient(patientID, function(response) {
            
            // some work around code for issue 44
            if ((typeof response.code != "undefined") && (typeof response.error == "object")) {
                //patient id is valid
                alert("Error patient ID is not valid");
            } else {
                AppSettings.setUserID(patientID);
                AppSettings.setPatientInfo(response);
                changePage("main-page");
            }
        });
    });
});