package blackjack.domain

data class Card(val suit: Suit, private val rank: Rank) {
    val scores: List<Int>
        get() = rank.scores

    val isAce: Boolean
        get() = rank.isAce

    val signature: String
        get() = "${rank.signature}${suit.signature}"
}