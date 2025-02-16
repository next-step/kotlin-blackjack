package blackjack.ui

import blackjack.domain.Gambler

object BlackjackReader {
    private const val NAME_SEPARATOR = ","

    const val YES_SIGN = "y"
    const val NO_SIGN = "n"

    private val Y_OR_N_REGEX = Regex("[${YES_SIGN}${NO_SIGN}${YES_SIGN.uppercase()}${NO_SIGN.uppercase()}]")

    fun readGamblerNames(): List<String> {
        BlackjackPrinter.askForPlayerName()
        return ConsoleReader.readLine()
            .split(NAME_SEPARATOR)
            .map { name -> name.trim() }
    }

    fun readDecisionForMoreCard(name: String): Boolean {
        BlackjackPrinter.askIfWantMoreCard(name)

        val readLine = ConsoleReader.readLine().lowercase()
        require(Y_OR_N_REGEX.matches(readLine)) { "$YES_SIGN 또는 ${NO_SIGN}만 입력해 주세요. (대소문자 구분 X) 현재 입력 = $readLine" }
        return readLine == YES_SIGN
    }

    fun readBetAmount(gamblerName: String): Long {
        BlackjackPrinter.askBetAmount(gamblerName)
        return ConsoleReader.readNumber()
    }
}
