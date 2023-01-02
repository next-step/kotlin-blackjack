package blackjack.domain.card

import java.util.Stack

class Cards(val cardStack: Stack<Card>) {

    val size = cardStack.size

    constructor(cards: List<Card>) : this(
        cardStack = cards.toCollection(Stack())
    )

    constructor(cardDeck: CardDeck) : this(
        List(INIT_COUNT) { cardDeck.draw() }
    )

    init {
        require(cardStack.size >= INIT_COUNT) { "최초 카드 ${INIT_COUNT}장 이상이여야 합니다." }
    }

    fun add(card: Card) {
        cardStack.push(card)
    }

    fun point(): Int =
        cardStack.fold(0) { acc, card ->
            acc + card.getPoint(acc)
        }

    companion object {
        const val INIT_COUNT = 2
    }
}
