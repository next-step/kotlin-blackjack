package blackjack.application

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

@JvmInline
value class Deck(private val cards: MutableList<PlayingCard>) {
    constructor(vararg card: PlayingCard) : this(card.toMutableList())

    fun getCard(): PlayingCard {
        if (cards.isEmpty()) {
            throw NoSuchElementException("카드가 없습니다.")
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
