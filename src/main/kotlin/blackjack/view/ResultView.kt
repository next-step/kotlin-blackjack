package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import blackjack.domain.Player

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "딜러와 %s에게 2장을 나누었습니다."
    private const val PLAYER_CARD_MESSAGE = "%s 카드: %s"
    private const val PLAYER_RESULT_MESSAGE = "%s 카드: %s - 결과: %d"
    private const val UNKNOWN_CARD_NUMBER_EXCEPTION_MESSAGE = "알 수 없는 카드 숫자입니다."
    private const val UNKNOWN_CARD_SHAPE_EXCEPTION_MESSAGE = "알 수 없는 카드 모양입니다."
    private const val DEALER_CARD_MESSAGE = "\n딜러 카드: %s - 결과: %d"
    private const val GAME_RESULT_MESSAGE = "%s: %s"
    private const val DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_NO_DRAW_MESSAGE = "\n딜러는 17 이상이라 추가로 카드를 받지 않았습니다."

    fun printSplitCardResult(players: List<Player>) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(players.joinToString(", ") { it.name }))
    }

    fun printDealerInitialCard(dealer: Dealer) {
        println("딜러: ${formatCard(dealer.cards[0])}")
    }

    fun printGameResult(
        results: Map<String, String>,
        dealerResult: String,
    ) {
        println("\n## 최종 승패")
        println(GAME_RESULT_MESSAGE.format("딜러", dealerResult))
        results.forEach { (name, result) ->
            println(GAME_RESULT_MESSAGE.format(name, result))
        }
    }

    fun printPlayerCards(players: List<Pair<String, List<Card>>>) {
        players.forEach { (name, cards) ->
            println(PLAYER_CARD_MESSAGE.format(name, formatCards(cards)))
        }
    }

    fun printFinalScores(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println(DEALER_CARD_MESSAGE.format(formatCards(dealer.cards), dealer.score))
        players.forEach { player ->
            println(PLAYER_RESULT_MESSAGE.format(player.name, formatCards(player.cards), player.score))
        }
    }

    fun printDealerDrawMessage(isDraw: Boolean) {
        println(if (isDraw) DEALER_DRAW_MESSAGE else DEALER_NO_DRAW_MESSAGE)
    }

    private val cardNumberDisplay =
        mapOf(
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

    private val cardShapeDisplay =
        mapOf(
            CardShape.HEART to "하트",
            CardShape.CLUB to "클로버",
            CardShape.SPADE to "스페이드",
            CardShape.DIAMOND to "다이아",
        )

    private fun formatCard(card: Card): String {
        val number = cardNumberDisplay[card.number] ?: throw IllegalStateException(UNKNOWN_CARD_NUMBER_EXCEPTION_MESSAGE)
        val shape = cardShapeDisplay[card.shape] ?: throw IllegalStateException(UNKNOWN_CARD_SHAPE_EXCEPTION_MESSAGE)
        return "$number$shape"
    }

    private fun formatCards(cards: List<Card>): String {
        return cards.joinToString(", ") { formatCard(it) }
    }
}
