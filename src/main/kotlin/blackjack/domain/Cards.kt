package blackjack.domain

class Cards {
    private val cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        cards.add(card)
    }

    fun get(): List<Card> {
        return cards
    }

    fun calculateScore(): Int {
        var sum = cards.sumOf { it.denomination.value }
        var aceCount = countAce()

        while (aceCount > 0 && sum + ADD_AMOUNT_IF_ACE_11 <= Game.BLACKJACK_SCORE) {
            sum += ADD_AMOUNT_IF_ACE_11
            aceCount--
        }
        return sum
    }

    private fun countAce(): Int {
        return cards.count { it.isAce() }
    }

    companion object {
        private const val ADD_AMOUNT_IF_ACE_11 = 10
    }
}
