package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    fun score(): Score {
        val sum = cards.sumOf { it.denomination.score }

        if (!hasAce()) {
            return Score(sum)
        }

        return Score(calculateWithAce(sum))
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun cards(): List<Card> {
        return cards.toList()
    }

    private fun calculateWithAce(sum: Int): Int {
        if (sum <= 11) {
            return sum + 10
        }

        return sum
    }

    private fun hasAce(): Boolean {
        return cards.any { it.denomination == Denomination.ACE }
    }
}
