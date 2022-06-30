package blackjack.view.response

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.Player

class PlayerResponse(
    val name: String,
    val hand: List<CardResponse>
) {

    companion object {
        fun from(player: Player): PlayerResponse {
            return PlayerResponse(
                player.name,
                player.hand.cards.map { CardResponse.from(it) }
            )
        }
    }
}

class CardResponse private constructor(
    val symbol: String,
    val suit: String
) {
    companion object {
        fun from(card: Card): CardResponse {
            val symbol = toSymbol(card.number)
            val suit = toSuit(card.suit)

            return CardResponse(symbol, suit)
        }

        private fun toSymbol(cardNumber: CardNumber): String {
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

        private fun toSuit(cardSuit: CardSuit): String {
            return when (cardSuit) {
                CardSuit.DIAMOND -> "다이아몬드"
                CardSuit.HEART -> "하트"
                CardSuit.CLOVER -> "클로버"
                CardSuit.SPADE -> "스페이드"
            }
        }
    }
}
