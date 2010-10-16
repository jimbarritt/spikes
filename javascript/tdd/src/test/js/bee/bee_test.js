TestCase("BeeTest", {

    setUp : function() {        
    },

    tearDown : function(){
    },

    testCanWaggle : function () {
        var bee = bee.Bee();

        var numberOfWaggles = bee.waggle();

        assertEquals(9, numberOfWaggles);
    }
});
