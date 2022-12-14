package blackjack.model

class Card(val suit: Suit, val denomination: Denomination) {
    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }

    fun getScore(): Int {
        return denomination.score
    }
}
