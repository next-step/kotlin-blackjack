package domain

class CardDeck {

    private val cards: MutableList<Card> = CardShape.values().flatMap { shape ->
        CardNumber.values().map { number ->
            Card(shape, number)
        }
    }.toMutableList()

    fun shuffle() {
        this.cards.shuffle()
    }

    fun getTopCard(): Card {
        check(this.cards.isNotEmpty()) { "카드가 다 떨어졌습니다." }
        return this.cards.removeFirst()
    }
}
