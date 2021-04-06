package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Value

class Hand(val cards: Cards = Cards()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun sumValues(): Value {
        return cards.sum()
    }
}
