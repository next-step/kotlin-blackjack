package blackjack.domain

import java.util.Stack

class BlackJackDeck(cards: List<BlackJackCard>) {
    private val cardsStack: Stack<BlackJackCard> = Stack()

    init {
        cards.forEach { cardsStack.push(it) }
    }

    fun draw(): BlackJackCard {
        require(cardsStack.isNotEmpty()) { "카드가 없어요" }
        return cardsStack.pop()
    }
}
