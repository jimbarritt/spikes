TestCase("isDefined behaviours", {

    setUp : function() {},
    tearDown : function(){},

    test_knows_if_an_object_is_defined : function() {
        var somethingIDefined = "foo";

        var isNotDefined = lang.isDefined(somethingIDefined);

        assertEquals("This object should be defined: ", true, isNotDefined);
    },

    test_knows_if_an_object_is_not_defined : function() {
        var isNotDefined = lang.isNotDefined(somethingIDefined);

        var somethingIDefined;
        assertEquals("Object should not be defined: ", true, isNotDefined);
    }

});
