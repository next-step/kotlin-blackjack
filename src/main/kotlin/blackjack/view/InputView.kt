package blackjack.view

import blackjack.domain.participant.Player

object InputView {
    private const val NAME_STRING = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BETTING_AMOUNT_STRING = "의 배팅 금액은?"
    private const val PLAY_STRING = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val ANSWER_EXCEPTION = "잘못된 답변입니다"

    fun getNames(): List<Player> {
        println(NAME_STRING)
        return readln().split(",").map { Player(it) }
    }

    fun getAnswer(player: Player): Boolean {
        println("${player.name}$PLAY_STRING")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException(ANSWER_EXCEPTION)
        }
    }

    fun getBettingAmount(player: Player): Int {
        println("\n${player.name}$BETTING_AMOUNT_STRING")
        return readln().toInt()
    }
}
