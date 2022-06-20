package blackjack.factory

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Cards
import blackjack.card.Suit

object SimpleCardCreator {

    fun startCard(): Cards {
        return Cards(
            * Suit.values().flatMap { suit ->
                CardSymbol.values()
                    .map { symbol -> Card(suit, symbol) }
            }.toTypedArray()
        )
    }
}
