package blackjack.strategy.shuffle

import blackjack.domain.card.Card

object CardsRandomShuffleStrategy : CardsShuffleStrategy {
    override fun shuffle(deck: List<Card>): List<Card> = deck.shuffled()
}
