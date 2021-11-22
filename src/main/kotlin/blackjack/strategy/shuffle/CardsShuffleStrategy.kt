package blackjack.strategy.shuffle

import blackjack.domain.card.Card

fun interface CardsShuffleStrategy {
    fun shuffle(lottoNumbers: List<Card>): List<Card>
}
