package blackjack.domain

import java.util.Stack

class Deck(
    val cards: Cards = RandomCardsGenerator.generate()
) {
    val cardSize
        get() = cardDeck.size

    private val cardDeck = Stack<Card>().apply { addAll(cards.values) }

    init {
        require(cards.size == TOTAL_CARD_SIZE) { "52장의 카드가 준비되어야 게임을 시작할 수 있습니다" }
    }

    fun draw(): Card {
        check(cardDeck.isNotEmpty()) { "카드가 모두 소진되었습니다." }
        return cardDeck.pop()
    }

    fun draw(size: Int): Cards {
        return Cards(List(size) { draw() })
    }

    companion object {
        const val INITIAL_DEAL_SIZE = 2
        const val TOTAL_CARD_SIZE = 52
    }
}
