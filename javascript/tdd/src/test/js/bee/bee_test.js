BeeTest = jstestdriver.TestCase("BeeTest", {

    setUp : function() {
    },

    tearDown : function(){
    },

    canWaggle : function () {
        var console = io.Console();
        var bee = bee.Bee(console);

        var numberOfWaggles = bee.waggle();

        assertEquals(9, numberOfWaggles);
    }
});
