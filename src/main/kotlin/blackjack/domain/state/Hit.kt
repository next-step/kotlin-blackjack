package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Score

class Hit(private val deck: Deck) : State {

    init {
        require(deck.size >= 2) { "deck size must be greater than equal to 2" }
        require(deck.score().isLessThanEqualToBlackjack) { "deck score must be less than equal to 21" }
    }

    override fun draw(card: Card): State {
        val deck = deck + card
        if (deck.isBurst()) return Burst(deck)
        return Hit(deck)
    }

    override fun score(): Score {
        return deck.score()
    }

    override fun stay(): State {
        return Stay(deck)
    }

    override fun currentDeck(): Deck {
        return this.deck
    }
}
