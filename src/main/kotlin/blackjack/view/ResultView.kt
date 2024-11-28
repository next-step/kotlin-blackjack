package blackjack.view

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "%s, %s에게 2장씩 나누었습니다."

    fun printSplitCardResult(playerName: String, anotherPlayerName: String) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(playerName, anotherPlayerName))
    }
}