package blackjack.view

import blackjack.domain.*

object ResultView {
    private const val SPLIT_CARD_RESULT_MESSAGE = "딜러와 %s에게 2장을 나누었습니다."
    private const val PLAYER_CARD_MESSAGE = "%s 카드: %s"
    private const val PLAYER_RESULT_MESSAGE = "%s 카드: %s - 결과: %d"
    private const val DEALER_CARD_MESSAGE = "\n딜러 카드: %s - 결과: %d"
    private const val GAME_RESULT_MESSAGE = "%s: %s"
    private const val DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_NO_DRAW_MESSAGE = "\n딜러는 17 이상이라 추가로 카드를 받지 않았습니다."

    fun printSplitCardResult(players: List<Player>) {
        println(SPLIT_CARD_RESULT_MESSAGE.format(players.joinToString(", ") { it.name }))
    }

    fun printDealerInitialCard(dealer: Dealer) {
        println("딜러: ${dealer.cards[0].formatDisplay()}")
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
            println(PLAYER_CARD_MESSAGE.format(name, cards.joinToString(", ") { it.formatDisplay() }))
        }
    }

    fun printFinalScores(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println(DEALER_CARD_MESSAGE.format(dealer.cards.joinToString(", ") { it.formatDisplay() }, dealer.score))
        players.forEach { player ->
            println(PLAYER_RESULT_MESSAGE.format(player.name, player.cards.joinToString(", ") { it.formatDisplay() }, player.score))
        }
    }

    fun printDealerDrawMessage(isDraw: Boolean) {
        println(if (isDraw) DEALER_DRAW_MESSAGE else DEALER_NO_DRAW_MESSAGE)
    }

    private val CardNumber.display: String
        get() = when (this) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }

    private val CardShape.display: String
        get() = when (this) {
            CardShape.HEART -> "하트"
            CardShape.CLUB -> "클로버"
            CardShape.SPADE -> "스페이드"
            CardShape.DIAMOND -> "다이아"
        }

    private fun Card.formatDisplay(): String = "${number.display}${shape.display}"
}
