package blackjack.model

class Card(val suit: Suit, val denomination: Denomination) {
    companion object {
        fun of(suit: Suit, denomination: Denomination): Card {
            return Card(suit, denomination)
        }
    }
}
