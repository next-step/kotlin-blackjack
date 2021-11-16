package blackjack.strategy.shuffle

import blackjack.domain.playingcard.PlayingCard

object DeckRandomShuffleStrategy : DeckShuffleStrategy {
    override fun shuffle(deck: List<PlayingCard>): List<PlayingCard> = deck.shuffled()
}
