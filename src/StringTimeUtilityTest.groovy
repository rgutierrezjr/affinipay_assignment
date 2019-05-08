class StringTimeUtilityTest extends GroovyTestCase {

    void testAddMinutes() {
        def utility = new StringTimeUtility()

        def newTime = utility.addMinutes("5:00 AM", 120)

        assertTrue(newTime == "7:00 AM")
    }

    void testPeriodChange() {
        def utility = new StringTimeUtility()

        def newTime = utility.addMinutes("10:00 AM", 120)

        assertTrue(newTime == "12:00 PM")
    }

    void testMissingTime() {
        def utility = new StringTimeUtility()

        def message = shouldFail(StringTimeUtilityException) {
            utility.addMinutes("", 120)
        }

        assertEquals 'Error: Time is required. Please enter a valid time and try again.', message
    }

    void testInvalidFormat() {
        def utility = new StringTimeUtility()

        def message = shouldFail(StringTimeUtilityException) {
            utility.addMinutes("99:99 AA", 120)
        }

        assertEquals 'Error: Invalid time format. Format [H]H:MM {AM|PM} required. Please enter a valid time and try again.', message
    }

    void testInvalidHour() {
        def utility = new StringTimeUtility()

        def message = shouldFail(StringTimeUtilityException) {
            utility.addMinutes("15:00 PM", 120)
        }

        assertEquals 'Error: Invalid time format. 12 hour format required. Please enter a valid time and try again.', message
    }


}
