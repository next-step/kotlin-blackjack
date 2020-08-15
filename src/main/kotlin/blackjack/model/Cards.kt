package blackjack.model

class Cards(cards: List<Card>) {
    private val cards = cards

    fun score(): Int {
        var sum = 0
        cards.forEach {
            sum = Denomination.sum(sum, it.denomination)
        }
        return sum
    }

    override fun toString(): String {
        return "${cards.joinToString { it.toString() }}"
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
