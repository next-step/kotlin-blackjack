package blackjack.domain

data class Card(val suit: Suit, val rank: Rank) {
    val scores: List<Int>
        get() = rank.scores

    val isAce: Boolean
        get() = rank.isAce
}
