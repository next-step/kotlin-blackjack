package blackjack.domain

const val INITIAL_CARD_COUNT = 2

class Deck {
    private var _cards = mutableListOf<Card>()

    init {
        _cards = Card.CARD_DECK.shuffled().toMutableList()
    }

    fun count(): Int = _cards.size

    fun divideInitialCards(): Cards {
        return Cards((1..INITIAL_CARD_COUNT).map { draw() })
    }

    fun draw(): Card {
        check(_cards.size > 0) { "뽑을 수 있는 카드가 없습니다." }
        return _cards.removeFirst()
    }
}
