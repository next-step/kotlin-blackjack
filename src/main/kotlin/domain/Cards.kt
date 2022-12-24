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

    fun score(): Int = this.cards.sumOf { it.number.primaryScore }
    fun secondaryScore(): Int = this.cards.sumOf { it.number.secondaryScore }
    fun cardList(): List<Card> = this.cards
    fun isExceedsAvailableReceiveNumber(): Boolean = this.score() <= RECEIVE_LIMIT_SCORE

    private companion object {
        const val RECEIVE_LIMIT_SCORE = 21
    }
}
