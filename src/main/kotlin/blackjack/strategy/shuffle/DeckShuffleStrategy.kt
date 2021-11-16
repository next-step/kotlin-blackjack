package blackjack.strategy.shuffle

import blackjack.domain.playingcard.PlayingCard

fun interface DeckShuffleStrategy {
    fun shuffle(lottoNumbers: List<PlayingCard>): List<PlayingCard>
}
