class StringTimeUtility {

    /**
     * This utility will take String parameter "time", format "[H]H:MM {AM|PM}", and add
     * Integer "minutes" to it and return the result as String "newTime".
     *
     * Method will throw a StringTimeUtilityException if inputs fail validation or any errors
     * are caught.
     *
     * @param time
     * @param minutes
     * @return newTime
     * @throws StringTimeUtilityException
     */
    def addMinutes(String time, Integer minutes) throws StringTimeUtilityException {
        def newTime = ""

        if (!time || time.isEmpty()) {
            throw new StringTimeUtilityException("Error: Time is required. Please enter a valid time and try again.")
        }

        def validTimeRegex = /[0-1]*[0-9]:[0-5][0-9] (AM|PM)/

        if (!(time ==~ validTimeRegex)) {
            throw new StringTimeUtilityException("Error: Invalid time format. Format [H]H:MM {AM|PM} required. Please enter a valid time and try again.")
        }

        // tokenize the time, take time and pm/am
        def tokenized = time.tokenize(" ")

        def hourAndMinutes = tokenized[0]
        def period = tokenized[1]

        tokenized = hourAndMinutes.tokenize(":")

        def timeHour = tokenized[0].toInteger()
        def timeMinutes = tokenized[1].toInteger()

        if (timeHour > 12) {
            throw new StringTimeUtilityException("Error: Invalid time format. 12 hour format required. Please enter a valid time and try again.")
        }

        // derive hours and remaining minutes to add.
        Integer addHours = minutes / 60
        Integer addMinutes = minutes % 60

        addMinutes += timeMinutes

        // derive extra hour and remaining minutes to add.
        addHours += addMinutes / 60
        addMinutes = addMinutes % 60

        // derive final 12 hour format
        timeHour = (addHours + timeHour) % 12
        timeHour = timeHour == 0 ? 12 : timeHour

        // build final time string.
        newTime = "${timeHour}:"
        newTime += "${addMinutes ?: "00"} "

        // derive period.
        if (period == "AM" && (addHours + timeHour) > 12) {
            newTime += "PM"
        } else {
            newTime += "AM"
        }

        return newTime
    }
}
