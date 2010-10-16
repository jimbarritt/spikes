var bee = {};

bee.Bee = function() {

    var external = {
        waggle : function() {
            console.log("Hey, I'm a bee, watch me waggle!")
            return 9;
        }

    };

    return external;

};