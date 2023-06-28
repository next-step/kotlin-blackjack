package blackjack.common.service

import blackjack.common.domain.Deck
import blackjack.common.domain.PokerCard

class DeckManager(
    private val deck: Deck = Deck()
) {
    fun drawTwoCards(): Array<PokerCard> {
        return arrayOf(deck.drawCard(), deck.drawCard())
    }

    fun draw(): PokerCard {
        return deck.drawCard()
    }
}
