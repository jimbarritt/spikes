TestCase("BeeTest", {

    setUp : function() {        
    },

    tearDown : function(){
    },

    testCanWaggle : function () {
        var console = io.Console();
        var bee = bee.Bee(console);

        var numberOfWaggles = bee.waggle();

        assertEquals(9, numberOfWaggles);
    }
});
