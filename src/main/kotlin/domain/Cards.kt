package domain

class Cards {
    private var cards: List<Card> = listOf()

    val count: Int
        get() = this.cards.size

    fun add(card: Card) {
        val mutableList = this.cards.toMutableList()
        mutableList.add(card)
        this.cards = mutableList.toList()
    }
}