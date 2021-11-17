package blackjack.strategy.shuffle

import blackjack.domain.card.Card

fun interface DeckShuffleStrategy {
    fun shuffle(lottoNumbers: List<Card>): List<Card>
}
