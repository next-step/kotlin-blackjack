package blackjack.util

import blackjack.domain.Deck

class RandomCardSelector : CardSelector() {
    override val deck: Deck = Deck.getShuffledDeck()
}
