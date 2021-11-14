package blackjack.domain

import java.util.Stack

class Deck() {
    val cards = generateCards()

    fun drawCard(): Card {
        return cards.pop()
    }

    private fun generateCards(): Stack<Card> {
        return Stack<Card>().apply {
            addAll(CardFactory.generateCards())
        }
    }
}
