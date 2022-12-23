package blackjack.model

class Card(val suit: Suit, val denomination: Denomination) {
    fun isAce(): Boolean {
        return denomination.isAce()
    }

    fun getScore(): Int {
        return denomination.score
    }

    fun getDisplayName(): String {
        return "${denomination.displayName}${suit.displayName}"
    }
}
