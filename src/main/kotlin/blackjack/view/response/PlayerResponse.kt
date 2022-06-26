package blackjack.view.response

import blackjack.domain.Card
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
    val cardNumber: String,
    val cardSuit: String
) {
    companion object {
        fun from(card: Card): CardResponse {
            return CardResponse(card.cardNumber.symbol, card.cardSuit.suit)
        }
    }
}
