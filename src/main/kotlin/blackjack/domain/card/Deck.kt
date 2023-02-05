package blackjack.domain.card

import blackjack.domain.card.strategy.ShuffleStrategy

class Deck(private val shuffleStrategy: ShuffleStrategy) {
    private var cards = mutableListOf<PlayingCard>()

    fun getCard(): PlayingCard {
        if (cards.isEmpty()) {
            cards.addAll(PlayingCards.shuffle(this.shuffleStrategy))
        }
        return cards.removeAt(FIRST_INDEX)
    }

    fun getCards(amount: Int): PlayingCards {
        return PlayingCards((1..amount).map { getCard() })
    }

    companion object {
        private const val FIRST_INDEX = 0
    }
}
