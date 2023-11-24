package blackjack.deck

import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit

internal class StandardCardProvider : CardProvider {
    override fun provideCards(): List<Card> =
        CardSuit.values().flatMap { suit ->
            CardRank.values().map { rank -> Card(suit, rank) }
        }
}
