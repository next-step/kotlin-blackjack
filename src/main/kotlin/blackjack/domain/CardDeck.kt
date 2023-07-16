package blackjack.domain

import blackjack.domain.Hands.Companion.INIT_CARD_SIZE
import java.util.*

class CardDeck {
    private val deck: Stack<Card>

    init {
        val stack: Stack<Card> = Stack()
        stack.addAll(Card.CARD_DECK.values.shuffled())
        deck = stack
    }

    fun firstDraw(): List<Card> {
        return List(INIT_CARD_SIZE) { draw() }
    }

    fun draw(): Card {
        require(deck.isNotEmpty()) { "덱의 모든 카드를 소진했습니다." }

        return deck.pop()
    }
}
