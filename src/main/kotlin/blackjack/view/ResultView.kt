package blackjack.view

import blackjack.dto.PlayerDto

object ResultView {
    private const val GAME_START_MESSAGE = "에게 2장의 나누었습니다."
    private const val CARD = "카드: "
    private const val GAME_RESULT_MESSAGE = " - 결과: "
    private const val LINE_FEED = "\r\n"

    fun printGameStartMessage(names: List<String>) {
        println(LINE_FEED + names.joinToString() + GAME_START_MESSAGE)
    }

    fun printPlayerCards(player: PlayerDto) {
        println(getNameAndCards(player))
    }

    fun printResultWithScore(player: PlayerDto) {
        println(getNameAndCards(player) + GAME_RESULT_MESSAGE + player.getScore())
    }

    fun printLineFeed() {
        println()
    }

    private fun getNameAndCards(player: PlayerDto): String {
        return player.getName() + CARD + player.getCards()
    }
}
