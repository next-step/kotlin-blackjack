package blackjack.model

const val ACE_VALUE_1 = 1
const val ACE_VALUE_11 = 11
const val ACE_VALUE_GAP = ACE_VALUE_11 - ACE_VALUE_1

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${suit.name} ${denomination.initial()}"
    }
}

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum class Denomination(private val score: Int) {
    A(ACE_VALUE_1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    fun initial() = if (this.score in 2..9) this.score.toString() else this.name
    fun score() = score
    fun isAce() = this == A
    fun scoreWithAce(score: Int): Int = if ((WIN_SCORE - ACE_VALUE_GAP) >= score) score + ACE_VALUE_GAP else score
}
