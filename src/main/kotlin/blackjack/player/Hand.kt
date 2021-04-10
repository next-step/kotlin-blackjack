package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Value

class Hand(val cards: Cards = Cards()) {
    enum class Status {
        BUST,
        NOT_BUST,
    }

    val status: Status
        get() {
            if (calculateValues() > Value.BLACKJACK_THRESHOLD) {
                return Status.BUST
            }
            return Status.NOT_BUST
        }

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateValues(): Value {
        return cards.toList()
            .sortedBy { it.symbol }
            .fold(Value.ZERO) { sum, card -> sum + card.valueBy(sum) }
    }
}
