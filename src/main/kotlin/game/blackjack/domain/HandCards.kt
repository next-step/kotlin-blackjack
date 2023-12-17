package game.blackjack.domain

class HandCards {
    private val handCards: MutableList<Card> = mutableListOf()
    val size: Int get() = handCards.size

    fun add(card: Card) = handCards.add(card)

    fun addAll(cards: List<Card>) = handCards.addAll(cards)

    fun getCurrentScore(): Int {
        var score = 0
        handCards.forEach { score += it.number.getScore(score) }

        return score
    }

    override fun toString() = handCards.joinToString(", ") { it.toString() }
}
