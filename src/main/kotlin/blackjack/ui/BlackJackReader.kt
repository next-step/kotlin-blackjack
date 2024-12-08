package blackjack.ui

import blackjack.domain.Gambler

object BlackJackReader {
    private val Y_OR_N_REGEX = Regex("[ynYN]")

    fun readGamblerNames(): List<String> {
        BlackJackPrinter.askForPlayerName()
        return ConsoleReader.readLine()
            .split(",")
            .map { name -> name.trim() }
    }

    fun readDecisionForMoreCard(gambler: Gambler): Boolean {
        BlackJackPrinter.askIfWantMoreCard(gambler)

        val readLine = ConsoleReader.readLine().lowercase()
        require(Y_OR_N_REGEX.matches(readLine)) { "y 또는 n만 입력해 주세요. (대소문자 구분 X)" }
        return readLine == "y"
    }
}
