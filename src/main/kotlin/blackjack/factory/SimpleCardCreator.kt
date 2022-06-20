package blackjack.factory

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.PlayCards
import blackjack.card.Suit

object SimpleCardCreator {

    fun startCard(): PlayCards {
        val cards = Suit.values().flatMap { suit ->
            CardSymbol.values()
                .map { symbol -> Card(suit, symbol) }
        }.toTypedArray()
            .also { it.shuffle() }

        return PlayCards(*cards)
    }
}
