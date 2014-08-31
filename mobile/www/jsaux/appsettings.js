var AppSettings = (function() {
   
    var localStorage = null;
    
    
    function AppSettings() {
        
    }
    
    function getLocalStorage() {
        try {
            if(window.localStorage ) {
                return window.localStorage;  
            }
        }
        catch (e) {
            return null;
        }        
    }
    
    AppSettings.getUserID = function() {
        if (localStorage === null) {
            localStorage = getLocalStorage();
        }
        if (localStorage === null) {
            return null;    
        }
        
        return localStorage.getItem("id");
    };
    
    AppSettings.setUserID = function(id) {
        if (localStorage === null) {
            localStorage = getLocalStorage();
        }
        if (localStorage === null) {
            return null;    
        }      
        localStorage.setItem("id", id);
    };
    
    AppSettings.hasUserID = function () {
        if (localStorage === null) {
            localStorage = getLocalStorage();
        }
        if (localStorage === null) {
            return false;    
        }
        
        if (AppSettings.getUserID() === null) {
            return false;    
        } else {
            return true;    
        }
    };
    
    AppSettings.reset = function() {
        if (localStorage === null) {
            localStorage = getLocalStorage();
        }
        if (localStorage === null) {
            return;    
        }
        
        localStorage.removeItem("id");
    };
    
    
    
    return AppSettings;
    
})();