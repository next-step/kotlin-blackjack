package blackjack.factory

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.PlayCards
import blackjack.card.Suit

object SimpleCardCreator {

    fun startCard(): PlayCards {
        return PlayCards(
            * Suit.values().flatMap { suit ->
                CardSymbol.values()
                    .map { symbol -> Card(suit, symbol) }
            }.toTypedArray()
        )
    }
}
