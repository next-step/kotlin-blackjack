package blackjack.view

import blackjack.domain.exception.NotNumericException
import blackjack.domain.player.Player

class InputView {

    fun enterPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln().split(",")
    }

    fun enterBattingAmountByPlayer(players: List<Player>) {
        players.map {
            println("${it.name}의 베팅 금액은?")
            it.gambleSummary.battingAmount = enterBattingAmount()
        }
    }

    private fun enterBattingAmount(): Int {
        val inputText = readln()
        validateNotString(inputText)
        return inputText.toInt()
    }

    private fun validateNotString(toCheck: String) {
        checkNotNull(toCheck.toIntOrNull()) { throw NotNumericException("베팅을 위해서는 숫자를 입력하셔야 합니다.") }
    }
}
