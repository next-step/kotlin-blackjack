package blackjack.domain.deck

class Card(val denomination: Denomination, val suit: Suit) {
    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }

    companion object {
        const val ACE_MIN_MAX_POINT_GAP = 10
    }
}
