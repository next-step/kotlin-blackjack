package game.blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    companion object {
        fun score(cards: List<Card>): Score {
            val denominations = cards.map { it.denomination }
            return denominations
                .sumOf { it.score }
                .sumIfSoftHand(denominations.contains(Denomination.ACE))
        }
    }
}
