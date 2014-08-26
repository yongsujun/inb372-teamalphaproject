var coordinates = [0,0];

function getLocation() {
    
    function suc(p){
        //console.log("geolocation success");
        if (p.coords.latitude != undefined) {
            coordinates[0] = p.coords.latitude;
            coordinates[1] = p.coords.longitude;
            
            return [lat, long];
        }
        
        
    };
    function fail() { 
        coordinates[0] = 0;
        coordinates[1] = null;
    };

    intel.xdk.geolocation.getCurrentPosition(suc,fail);
   
}