package blackjack.application

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.card.strategy.ShuffleStrategy

object Deck {
    private const val FIRST_INDEX = 0
    private var shuffleStrategy: ShuffleStrategy = RandomShuffleStrategy()
    private var cards = mutableListOf<PlayingCard>()

    fun setShuffleStrategy(shuffleStrategy: ShuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy
        cards.addAll(PlayingCards.shuffle(this.shuffleStrategy))
    }

    fun getCard(): PlayingCard {
        if (cards.isEmpty()) {
            cards.addAll(PlayingCards.shuffle(this.shuffleStrategy))
        }
        return cards.removeAt(FIRST_INDEX)
    }

    fun getCards(amount: Int): PlayingCards {
        return PlayingCards((1..amount).map { this.getCard() })
    }
}
