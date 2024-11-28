package blackjack.entity

data class Card(val suit: Suit, val rank: Rank) {
    fun describe(): String {
        return "${rank.displayName}${suit.displayName}"
    }
}
