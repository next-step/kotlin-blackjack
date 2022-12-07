package blackjack.domain.card.strategy

import blackjack.domain.card.PlayingCards

fun interface DeckGenerateStrategy {
    fun generate(): PlayingCards
}
