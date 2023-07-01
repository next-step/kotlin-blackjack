package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class RandomCardSelector : CardSelector {
    private val deck: Deck = Deck.getShuffledDeck()

    override fun drawCard(): Card {
        return deck.drawCard()
    }
}
