package blackjack.step2.view

import blackjack.step2.domain.Card
import blackjack.step2.domain.CardNumber
import blackjack.step2.domain.PlayerCard

object OutputView {
    fun printFirstDealtCard(playerCards: List<PlayerCard>) {
        playerCards.forEach { playerCard ->
            println("${playerCard.playerName} 카드: ${formatCards(playerCard.allCards)}")
        }
    }

    fun printCardResult(playerCards: List<PlayerCard>) {
        playerCards.forEach { playerCard ->
            println(
                "${playerCard.playerName} 카드: ${formatCards(playerCard.allCards)} (점수: ${playerCard.calculateScore()})",
            )
        }
    }

    private fun formatCards(cards: List<Card>): String {
        return cards.joinToString(", ") {
            "${formatCardNumber(it.number)}${it.type.value}"
        }
    }

    private fun formatCardNumber(cardNumber: CardNumber): String {
        return when (cardNumber) {
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
    }
}
