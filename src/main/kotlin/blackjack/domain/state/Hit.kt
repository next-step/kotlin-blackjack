package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Deck

class Hit(private val deck: Deck) : State {

    init {
        require(deck.size >= 2) { "deck size must be greater than equal to 2" }
    }

    fun draw(card: Card): State {
        deck.add(card)
        if (deck.isBurst()) return Burst()
        return Hit(deck)
    }
}
