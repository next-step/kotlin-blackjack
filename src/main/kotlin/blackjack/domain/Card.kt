package blackjack.domain

class Card(val rank: String, val suit: Suit) {
    fun getValue(defaultAceValue: Int = 11): Int {
        return when (rank) {
            "Ace" -> defaultAceValue
            "King", "Queen", "Jack" -> 10
            else -> rank.toInt()
        }
    }
}
