package blackjack.domain

const val INITIAL_CARD_COUNT = 2

class Deck(private val values: List<Card> = DEFAULT_CARDS) {

    private val _values: MutableList<Card> = values.toMutableList()
    
    fun draw(): Card {
        check(_values.isNotEmpty()) { "뽑을 수 있는 카드가 없습니다." }
        return _values.removeFirst()
    }

    fun drawInitCards(): Cards {
        return Cards((1..INITIAL_CARD_COUNT).map { draw() })
    }

    fun count() = _values.count()

    companion object {
        val DEFAULT_CARDS = Card.ALL_CARDS.shuffled()
        const val INITIAL_CARD_COUNT = 2
    }
}
