package blackjack.strategy.shuffle

import blackjack.domain.card.Card

object DeckRandomShuffleStrategy : DeckShuffleStrategy {
    override fun shuffle(deck: List<Card>): List<Card> = deck.shuffled()
}
