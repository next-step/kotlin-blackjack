package domain

class BlackjackCards() {
    val cards: MutableList<Card> = mutableListOf()

    companion object {
        const val BLACKJACK_MAX_SCORE = 21
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun calculateScore(): Int {
        var total = cards.sumOf { it.value.basicScore }
        var aceCount = cards.count { it.value == CardValue.ACE }

        while (total > BLACKJACK_MAX_SCORE && aceCount > 0) {
            total -= 10 // ACE 11 → 1 변경
            aceCount--
        }

        return total
    }

    fun displayCardInfo(): String {
        return cards.joinToString(", ") { it.toString() }
    }
}
