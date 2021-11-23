package blackjack.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

fun interface DrawStrategy {
    fun assign(deck: Deck): List<Card>
}
