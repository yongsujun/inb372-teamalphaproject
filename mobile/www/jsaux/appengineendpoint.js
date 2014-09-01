var AppEngineEndpoints = (function() {
    
    var ROOT = 'https://inb372-team-alpha.appspot.com/_ah/api';
    var hasLoadedPatientApi = false;
    function AppEngineEndpoints() {
        
    }
    
    AppEngineEndpoints.getPatient = function(id, cbFunction) {
        
        gapi.client.load("patient", "v1", function() {
            hasLoadedPatientApi = true;
            gapi.client.patient.get({"id":id}).execute(function(response) {
                cbFunction(response);
            });            
        }, ROOT);

    };
    
    return AppEngineEndpoints;
})();