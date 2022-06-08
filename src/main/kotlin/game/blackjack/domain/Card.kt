package game.blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    companion object {
        fun score(cards: List<Card>): Int = Denomination.score(cards.map { it.denomination })
    }
}
