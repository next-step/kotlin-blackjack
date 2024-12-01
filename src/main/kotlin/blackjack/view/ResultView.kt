package blackjack.view

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "%s, %s에게 2장씩 나누었습니다."
    private const val PLAYER_CARD_MESSAGE = "%s 카드: %s"

    fun printSplitCardResult(playerNames: List<String>) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(playerNames[0], playerNames[1]))
    }

    fun printPlayerCards(players: List<Pair<String, List<String>>>) {
        players.forEach { (name, cards) ->
            println(PLAYER_CARD_MESSAGE.format(name, cards.joinToString(", ")))
        }
    }
}
