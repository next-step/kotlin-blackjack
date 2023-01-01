package domains

@JvmInline
value class Cards(val cards: MutableList<Card> = mutableListOf()) {

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isDrawable(condition: Int): Boolean {
        return cards.sumOf { it.pokerNumber.number } < condition
    }

    fun sumOfNumbers(defeatedMaxNumber: Int): Int {
        if (hasAce()) {
            return sumWithAce(defeatedMaxNumber)
        }
        return cards.sumOf { it.pokerNumber.number }
    }

    private fun sumWithAce(defeatedMaxNumber: Int): Int {
        val summedNumber = cards.sumOf { it.pokerNumber.number }
        if (summedNumber + 10 <= defeatedMaxNumber) {
            return summedNumber + 10
        }
        return summedNumber
    }

    private fun hasAce(): Boolean {
        return cards.singleOrNull { it.pokerNumber.name == PokerNumber.ACE.toString() }?.let { true } ?: false
    }
}
