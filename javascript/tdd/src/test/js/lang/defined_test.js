TestCase("DefinedTest", {

    setUp : function() {},
    tearDown : function(){},

    testKnowsIfAnObjectIsNotDefined : function() {
        var somethingIDefined = "foo";

        var isNotDefined = lang.isNotDefined(somethingIDefined);

        assertEquals("This object should be defined: ", true, isNotDefined);
    }

});
