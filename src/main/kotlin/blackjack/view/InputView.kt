package blackjack.view

import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers

object InputView {
    private const val DELIMITER = ","

    fun getGamers(): Gamers {
        val names = readln().split(DELIMITER)
        val gamers = names.map { Gamer(it.trim()) }
        return Gamers(gamers)
    }

    fun getYesOrNo(gamer: Gamer): Boolean {
        return try {
            OutputView.printOneMoreCard(gamer)
            val input = readln()
            validateYesOrNo(input)
            isYes(input)
        } catch (e: IllegalArgumentException) {
            e.message?.let { OutputView.printMessage(it) }
            getYesOrNo(gamer)
        }
    }

    private fun validateYesOrNo(input: String) {
        require(input.equals("y", ignoreCase = true) || input.equals("n", ignoreCase = true)) {
            "y 또는 n을 입력해주세요."
        }
    }

    private fun isYes(input: String) = input == "y"
}
