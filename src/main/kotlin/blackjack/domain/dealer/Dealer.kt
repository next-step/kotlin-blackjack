package blackjack.domain.dealer

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck

class Dealer private constructor(
    private val deck: Deck = Deck.release()
) {
    fun drawCard(): Card = deck.drawCard()
}
