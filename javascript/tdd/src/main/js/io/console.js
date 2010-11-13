var io = {};

io.NullConsole = function() {

    log : function(message) {
        // do nothing as console is not defined
    }
}

io.NullConsole.init = function() {
    if (lang.isNotDefined(console)) {
        
    }
};