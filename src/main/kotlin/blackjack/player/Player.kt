package blackjack.player

import blackjack.playingcard.Deck

class Player(val name: Name) {
    val hand: Hand = Hand()

    fun drawOneFrom(deck: Deck) {
        hand.add(deck.draw())
    }
}
