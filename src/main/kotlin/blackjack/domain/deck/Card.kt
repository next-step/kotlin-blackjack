package blackjack.domain.deck

data class Card(val suits: Suits, val denomination: Denomination) {
    override fun toString(): String {
        return "${denomination.value}${suits.value}"
    }
}
