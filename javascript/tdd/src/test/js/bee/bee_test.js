TestCase("bee behaviours", {

    setUp : function() {},

    tearDown : function() {},

    test_waggles : function () {
        assertEquals(true, lang.isDefined(bee));        
        var aBee = bee.Bee();

        var numberOfWaggles = aBee.waggle();

        assertEquals(9, numberOfWaggles);
    }
});
