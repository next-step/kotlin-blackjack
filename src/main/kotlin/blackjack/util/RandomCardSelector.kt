package blackjack.util

import blackjack.domain.Card
import blackjack.domain.Deck

class RandomCardSelector : CardSelector {
    val deck: Deck = Deck.getShuffledDeck()

    override fun drawCard(): Card {
        return deck.drawCard()
    }
}
