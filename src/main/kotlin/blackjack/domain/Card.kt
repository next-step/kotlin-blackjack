package blackjack.domain

class Card(val rank: Rank, val suit: Suit) {
    fun value(defaultAceValue: Int = 11): Int {
        return rank.getValue(defaultAceValue)
    }
}
