package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Value

class Hand(val cards: Cards = Cards()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateValues(): Value {
        return cards.toList()
            .sortedBy { it.symbol }
            .fold(Value.ZERO) { sum, card -> sum + card.valueBy(sum) }
    }
}
