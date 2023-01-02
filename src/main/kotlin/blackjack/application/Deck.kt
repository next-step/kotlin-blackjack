package blackjack.application

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy

@JvmInline
value class Deck(private val cards: MutableList<PlayingCard>) {
    constructor(vararg card: PlayingCard) : this(card.toMutableList())

    fun getCard(): PlayingCard {
        if (cards.isEmpty()) {
            cards.addAll(PlayingCards.shuffle(RandomShuffleStrategy()))
        }
        return cards.removeAt(FIRST_INDEX)
    }

    fun getCards(amount: Int): PlayingCards {
        return PlayingCards((1..amount).map { this.getCard() })
    }

    companion object {
        private const val FIRST_INDEX = 0
    }
}
