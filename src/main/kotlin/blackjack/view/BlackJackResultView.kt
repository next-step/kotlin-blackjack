package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardShape
import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.game.CardDistributionResult
import blackjack.domain.player.unWrappings

class BlackJackResultView {

    fun display(result: CardDistributionResult) {
        println()
        println(result.makeTitleMessage())
        result.playerCardDeckCaptures
            .map { playerCardDeckCapture -> playerCardDeckCapture.makeDisplayMessage() }
            .forEach { playerCardDeckCaptureMessage -> println(playerCardDeckCaptureMessage) }
    }

    fun display(playerCardDeckCapture: PlayerCardDeckCapture) {
        println(playerCardDeckCapture.makeDisplayMessage())
    }

    private fun CardDistributionResult.makeTitleMessage(): String {
        val names = playerNames.unWrappings().joinToString(", ")
        return "${names}에게 ${countOfCardDistribution}장씩 나누었습니다."
    }

    private fun PlayerCardDeckCapture.makeDisplayMessage(): String {
        val name = playerName.unWrapping()
        val cardNames = cards.joinToString(", ") { card -> card.makeDisplayMessage() }
        return "${name}카드: $cardNames"
    }

    private fun Card.makeDisplayMessage(): String {
        val shapeName = when (shape) {
            CardShape.SPADE -> "스페이드"
            CardShape.HEART -> "하트"
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.CLOVER -> "클로버"
        }
        val denominationName = when (denomination) {
            CardDenomination.ACE -> "A"
            CardDenomination.ONE -> "1"
            CardDenomination.TWO -> "2"
            CardDenomination.THREE -> "3"
            CardDenomination.FOUR -> "4"
            CardDenomination.FIVE -> "5"
            CardDenomination.SIX -> "6"
            CardDenomination.SEVEN -> "7"
            CardDenomination.EIGHT -> "8"
            CardDenomination.NINE -> "9"
            CardDenomination.JACK -> "J"
            CardDenomination.QUEEN -> "Q"
            CardDenomination.KING -> "K"
        }
        return "$denominationName$shapeName"
    }
}
