var AppEngineEndpoints = (function() {
    
   // var ROOT = 'https://inb372-team-alpha.appspot.com/_ah/api';
    var ROOT = "http://localhost:8888/_ah/api";
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
    
    AppEngineEndpoints.updateLocation = function(id, latitude, longitude) {
        var content = {
            "id": id,
            "latitude": latitude,
            "longitude": longitude
        };
        
        gapi.client.load("location", "v1", function() {
            gapi.client.location.update(content).execute(function(response) {
                console.log(response);
            }); 
        }, ROOT);
    };
    
    return AppEngineEndpoints;
})();