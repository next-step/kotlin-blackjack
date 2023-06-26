package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardHolder
import blackjack.domain.card.CardShape
import blackjack.domain.card.PlayerCards
import blackjack.domain.game.BlackJackGameResult
import blackjack.domain.game.CardDistributionResult
import blackjack.domain.player.unWrappings

class BlackJackResultView {

    fun display(result: CardDistributionResult) {
        println()
        println(result.makeTitleMessage())
        println(result.dealerCards.makeDisplayMessage())
        result.playerCards
            .map { playerCard -> playerCard.makeDisplayMessage() }
            .forEach { playerCardsCaptureMessage -> println(playerCardsCaptureMessage) }
        println()
    }

    fun display(playerCards: PlayerCards) {
        println(playerCards.makeDisplayMessage())
    }

    fun display(blackJackGameResult: BlackJackGameResult) {
        println()
        blackJackGameResult.playerGameResults
            .map { it.playerCards.makeDisplayMessage().plus(" - 결과: ${it.score}") }
            .forEach { println(it) }
    }

    private fun CardDistributionResult.makeTitleMessage(): String {
        val names = playerNames.unWrappings().joinToString(", ")
        return "딜러와 ${names}에게 ${distributionCardSize}장씩 나누었습니다."
    }

    private fun List<CardHolder>.makeDisplayMessage(): String {
        val cardsMessage = filterIsInstance<CardHolder.Open>().joinToString(", ") { openCard ->
            openCard.card.makeDisplayMessage()
        }
        return "딜러: $cardsMessage"
    }

    private fun PlayerCards.makeDisplayMessage(): String {
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
            CardDenomination.TWO -> "2"
            CardDenomination.THREE -> "3"
            CardDenomination.FOUR -> "4"
            CardDenomination.FIVE -> "5"
            CardDenomination.SIX -> "6"
            CardDenomination.SEVEN -> "7"
            CardDenomination.EIGHT -> "8"
            CardDenomination.NINE -> "9"
            CardDenomination.TEN -> "10"
            CardDenomination.JACK -> "J"
            CardDenomination.QUEEN -> "Q"
            CardDenomination.KING -> "K"
        }
        return "$denominationName$shapeName"
    }
}
