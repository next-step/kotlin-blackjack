package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Hand
import blackjack.domain.card.OpenCards

class Dealer(val cardDeck: CardDeck) : Playable(Hand.empty()) {
    fun fetchOpenCard(): OpenCards = OpenCards(cardDeck.fetch(), cardDeck.fetch())
    fun dealing(playable: Playable) {
        if (playable.isFinished()) {
            throw RuntimeException()
        }
        playable.hit(cardDeck.fetch())
    }

    fun hitSelf() = hit(cardDeck.fetch())

    fun shouldHit(): Boolean = total() <= SHOULD_HIT_SCORE

    fun firstOpenCard(): Card = hand.first()

    companion object {
        const val SHOULD_HIT_SCORE = 16
    }
}
