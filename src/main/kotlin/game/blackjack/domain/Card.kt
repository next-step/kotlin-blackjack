package game.blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    companion object {

        private const val BLACKJACK_SCORE = 21
        private const val ACE_EXTRA_SCORE = 10

        fun score(cards: List<Card>): Int {
            val denominations = cards.map { it.denomination }
            var total = denominations.sumOf { it.score }
            total += if (denominations.contains(Denomination.ACE) && total + ACE_EXTRA_SCORE <= BLACKJACK_SCORE) ACE_EXTRA_SCORE else 0
            return total
        }

        fun isBust(score: Int): Boolean = score > BLACKJACK_SCORE
    }
}
