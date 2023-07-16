package blackjack.domain.state

import blackjack.domain.BlackjackCardPointCalculator
import blackjack.domain.Card
import blackjack.domain.Deck

class Hit(private val deck: Deck) : State {

    init {
        require(deck.size >= 2) { "deck size must be greater than equal to 2" }
        require(deck.score() <= BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD) {
            "deck score must be less than equal to 21"
        }
    }

    fun draw(card: Card): State {
        deck.add(card)
        if (deck.isBurst()) return Burst()
        return Hit(deck)
    }

    fun stay(): State {
        return Stay()
    }
}
