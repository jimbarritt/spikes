TestCase("DefinedTest", {

    setUp : function() {},
    tearDown : function(){},

    test_knows_if_an_object_is_not_defined : function() {
        var somethingIDefined = "foo ".trim();

        var isNotDefined = lang.isNotDefined(somethingIDefined);

        assertEquals("This object should be defined: ", true, isNotDefined);
    }

});
