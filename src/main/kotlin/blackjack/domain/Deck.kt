package blackjack.domain

const val INITIAL_CARD_COUNT = 2

class Deck {
    private val cards = Card.ALL_CARDS.shuffled().toMutableList()

    fun count(): Int = cards.size

    fun divideInitialCards(): Cards {
        return Cards((1..INITIAL_CARD_COUNT).map { draw() })
    }

    fun draw(): Card {
        check(cards.isNotEmpty()) { "뽑을 수 있는 카드가 없습니다." }
        return cards.removeFirst()
    }
}
