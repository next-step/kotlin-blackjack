package blackjack.view

import blackjack.domain.BetAmount
import blackjack.domain.Player

object InputView {
    private const val YES = "y"
    private const val NO = "n"
    fun inputPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun inputBetAmount(name: String): BetAmount {
        println("$name 의 베팅 금액은?")
        return BetAmount(readln().toInt())
    }

    fun inputIsGetCard(player: Player): Boolean {
        println("${player.name.value}(은)는 한장의 카드를 더 받겠습니까? (예는 $YES, 아니오는 $NO)")
        val answer = validateAnswer(readln())
        return answer == YES
    }

    private fun validateAnswer(input: String): String {
        val answers = listOf(YES, NO)

        return if (answers.contains(input)) {
            input
        } else {
            throw IllegalArgumentException("$YES 또는 $NO 만 입력할 수 있습니다.")
        }
    }
}
