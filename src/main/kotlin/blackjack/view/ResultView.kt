package blackjack.view

import blackjack.dto.PlayerDto

object ResultView {
    private const val GAME_START_MESSAGE = "에게 2장의 나누었습니다."
    private const val CARD = "카드: "
    private const val HIT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val GAME_RESULT_MESSAGE = " - 결과: "
    private const val LINE_FEED = "\r\n"

    fun printGameStartMessage(names: List<String>) {
        println(LINE_FEED + names.joinToString() + GAME_START_MESSAGE)
    }

    fun printPlayerCards(player: PlayerDto) {
        println(player.getName() + CARD + player.getCards().joinToString(", "))
    }
}
