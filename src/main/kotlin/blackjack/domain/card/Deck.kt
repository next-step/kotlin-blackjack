package blackjack.domain.card

import java.util.Stack

class Deck() {
    private val cards = generateCards()

    fun drawCard(): Card {
        return cards.pop()
    }

    private fun generateCards(): Stack<Card> {
        return Stack<Card>().apply {
            addAll(CardFactory.generateCards().shuffled())
        }
    }
}
