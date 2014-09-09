
var LocationService = (function (){
    
    var latitude = 0; 
    var longitude = 0;

    
    function LocationService() {
        
        var pos = "my pos";

        this.getPos = function() {
            return pos;    
        };
    }
    
    LocationService.getLatitude = function() { 
        return latitude; 
    }; 
    
    LocationService.getLongitude = function() { 
        return longitude;
    };
    
    LocationService.updateLocation = function() {
        function suc(p){
            if (p.coords.latitude !== undefined) {
                latitude = p.coords.latitude;
                longitude = p.coords.longitude;
                
            }
        }
        function fail() { 
            latitude = null;
            longitude = null;
        }

        navigator.geolocation.getCurrentPosition(suc,fail);        
    };
    

    
    return LocationService;
})();


