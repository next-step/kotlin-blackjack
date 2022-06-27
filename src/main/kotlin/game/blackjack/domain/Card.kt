package game.blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    companion object {
        fun score(cards: List<Card>): Score {
            cards
            val denominations = cards.map { it.denomination }
            return denominations
                .sumOf { it.score }
                .calculateAceScore(denominations.count { it == Denomination.ACE })
        }
    }
}
