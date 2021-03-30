package blackjack.domain.card

class Card(val suit: Suit, val denomination: Denomination) {
    val isAce: Boolean
        get() = denomination.isAce

    val score: Int
        get() = denomination.score

    val displayName: String
        get() = "${suit.name} ${denomination.name}"
}
