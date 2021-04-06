package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards

class Hand(val cards: Cards) {
    fun add(card: Card) {
        cards.add(card)
    }
}
