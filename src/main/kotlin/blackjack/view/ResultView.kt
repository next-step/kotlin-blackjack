package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Player

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "%s, %s에게 2장씩 나누었습니다."
    private const val PLAYER_CARD_MESSAGE = "%s 카드: %s"
    private const val PLAYER_RESULT_MESSAGE = "%s 카드: %s - 결과: %d"

    private val cardNumberDisplay = mapOf(
        CardNumber.ACE to "A",
        CardNumber.TWO to "2",
        CardNumber.THREE to "3",
        CardNumber.FOUR to "4",
        CardNumber.FIVE to "5",
        CardNumber.SIX to "6",
        CardNumber.SEVEN to "7",
        CardNumber.EIGHT to "8",
        CardNumber.NINE to "9",
        CardNumber.TEN to "10",
        CardNumber.JACK to "J",
        CardNumber.QUEEN to "Q",
        CardNumber.KING to "K",
    )

    private val cardShapeDisplay = mapOf(
        CardShape.HEART to "하트",
        CardShape.CLUB to "클로버",
        CardShape.SPADE to "스페이드",
        CardShape.DIAMOND to "다이아"
    )

    private fun formatCard(card: Card): String {
        val number = cardNumberDisplay[card.number] ?: throw IllegalStateException("Unknown card number")
        val shape = cardShapeDisplay[card.shape] ?: throw IllegalStateException("Unknown card shape")
        return "$number$shape"
    }

    private fun formatCards(cards: List<Card>): String {
        return cards.joinToString(", ") { formatCard(it) }
    }

    fun printSplitCardResult(playerNames: List<String>) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(playerNames[0], playerNames[1]))
    }

    fun printPlayerCards(players: List<Pair<String, List<Card>>>) {
        players.forEach { (name, cards) ->
            println(PLAYER_CARD_MESSAGE.format(name, formatCards(cards)))
        }
    }

    fun printFinalScores(
        scores: List<Pair<String, Int>>,
        players: List<Player>,
    ) {
        scores.forEach { (name, score) ->
            val cards = players.first { it.name == name }.cards
            println(PLAYER_RESULT_MESSAGE.format(name, formatCards(cards), score))
        }
    }
}
