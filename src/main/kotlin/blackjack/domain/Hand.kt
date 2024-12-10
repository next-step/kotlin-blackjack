package blackjack.domain

class Hand(private val cards: List<Card> = listOf()) {
    fun addCards(newCards: List<Card>): Hand {
        return Hand(cards + newCards)
    }

    fun calculateScore(): Int {
        val regularCardSum =
            cards
                .filter { it.number != CardNumber.ACE }
                .sumOf { it.number.getPoints() }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        return when (aceCount) {
            0 -> regularCardSum
            1 -> {
                val oneAceScore = regularCardSum + 11
                if (oneAceScore > 21) regularCardSum + 1 else oneAceScore
            }
            else -> {
                val allAcesAs11 = regularCardSum + 11 + (aceCount - 1)
                if (allAcesAs11 > 21) regularCardSum + aceCount else allAcesAs11
            }
        }
    }

    fun getCards(): List<Card> = cards.toList()
}
