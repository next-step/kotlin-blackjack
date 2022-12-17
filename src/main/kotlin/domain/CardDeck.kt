package domain

class CardDeck {

    private var cards: List<Card>

    init {
        val mutableCardList = mutableListOf<Card>()
        CardShape.values().forEach { shape ->
            CardNumber.values().forEach { number ->
                val card = Card(shape = shape, number = number)
                mutableCardList.add(card)
            }
        }
        cards = mutableCardList.toList()
    }

    fun shuffle() {
        val mutableList = this.cards.toMutableList()
        mutableList.shuffle()
        this.cards = mutableList.toList()
    }

    fun topCard(): Card {
        check(this.cards.isNotEmpty()) { "카드가 다 떨어졌습니다." }
        return this.cards[0]
    }
}