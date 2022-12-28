package blackjack.domain

class Deck(values: List<Card> = DEFAULT_CARDS) {

    private val values: MutableList<Card> = values.toMutableList()

    fun draw(): Card {
        check(values.isNotEmpty()) { "뽑을 수 있는 카드가 없습니다." }
        return values.removeFirst()
    }

    fun draw(times: Int): Cards {
        return Cards((1..times).map { draw() })
    }

    fun count() = values.count()

    companion object {
        val DEFAULT_CARDS = Card.ALL_CARDS.shuffled()
    }
}
