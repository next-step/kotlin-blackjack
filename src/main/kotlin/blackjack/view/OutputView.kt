package blackjack.view

import blackjack.model.BlackjackPlayer
import blackjack.model.TrumpCard
import blackjack.model.TrumpCardNumber
import blackjack.model.TrumpCardShape

object OutputView {

    fun printInitialDealing(players: Collection<BlackjackPlayer>, count: Int) {
        println("${players.joinToString { it.name }}에게 ${count}장의 나누었습니다.")
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: BlackjackPlayer) {
        println(playerString(player))
    }

    fun printPlayerResult(player: BlackjackPlayer, score: Int) {
        println("${playerString(player)} - 결과: $score")
    }

    private fun playerString(player: BlackjackPlayer): String {
        return "${player.name}카드: ${player.deck.joinToString { cardString(it) }}"
    }

    private fun cardString(card: TrumpCard): String {
        return "${cardNumberString(card.number)}${cardShapeString(card.shape)}"
    }

    private fun cardShapeString(shape: TrumpCardShape) = when (shape) {
        TrumpCardShape.SPADE -> "스페이드"
        TrumpCardShape.HEART -> "하트"
        TrumpCardShape.DIAMOND -> "다이아몬드"
        TrumpCardShape.CLOVER -> "클로버"
    }

    private fun cardNumberString(number: TrumpCardNumber): String = when (number) {
        TrumpCardNumber.ACE -> "A"
        TrumpCardNumber.TWO -> "2"
        TrumpCardNumber.THREE -> "3"
        TrumpCardNumber.FOUR -> "4"
        TrumpCardNumber.FIVE -> "5"
        TrumpCardNumber.SIX -> "6"
        TrumpCardNumber.SEVEN -> "7"
        TrumpCardNumber.EIGHT -> "8"
        TrumpCardNumber.NINE -> "9"
        TrumpCardNumber.TEN -> "10"
        TrumpCardNumber.JACK -> "J"
        TrumpCardNumber.QUEEN -> "Q"
        TrumpCardNumber.KING -> "K"
    }
}
