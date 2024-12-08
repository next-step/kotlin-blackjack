package blackjack.domain

data class Card(val suit: Suit, private val rank: Rank) {
    val score: Int
        get() = rank.score
}