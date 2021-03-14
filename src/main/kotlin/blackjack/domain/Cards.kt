package blackjack.domain

class Cards(vararg elements: Card) {
    private val elements = elements.toMutableList()
    val score: Score
        get() {
            val firstCard = elements[0]
            val otherCards = elements.subList(1, elements.size)
            return firstCard.calculateScore(otherCards)
        }

    init {
        require(this.elements.size == 2)
    }

    fun add(card: Card) {
        elements.add(card)
    }
}
