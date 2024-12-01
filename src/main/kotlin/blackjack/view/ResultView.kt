package blackjack.view

import blackjack.domain.Player

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "%s, %s에게 2장씩 나누었습니다."
    private const val PLAYER_CARD_MESSAGE = "%s 카드: %s"
    private const val PLAYER_RESULT_MESSAGE = "%s 카드: %s - 결과: %d"

    fun printSplitCardResult(playerNames: List<String>) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(playerNames[0], playerNames[1]))
    }

    fun printPlayerCards(players: List<Pair<String, List<String>>>) {
        players.forEach { (name, cards) ->
            println(PLAYER_CARD_MESSAGE.format(name, cards.joinToString(", ")))
        }
    }

    fun printFinalScores(
        scores: List<Pair<String, Int>>,
        players: List<Player>,
    ) {
        scores.forEach { (name, score) ->
            val cards = players.first { it.name == name }.cards
            println(PLAYER_RESULT_MESSAGE.format(name, cards.joinToString(", "), score))
        }
    }
}
