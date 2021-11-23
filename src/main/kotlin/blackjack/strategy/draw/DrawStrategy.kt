package blackjack.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

fun interface DrawStrategy {
    fun draw(deck: Deck): List<Card>
}
