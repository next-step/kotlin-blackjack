package blackjack.domain.card

enum class CardNumber(val value: Int) {
    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    K(10),
    Q(10),
    J(10);

    override fun toString(): String {
        return when (this) {
            K -> "K"
            Q -> "Q"
            J -> "J"
            A -> "A"
            else -> value.toString()
        }
    }

    companion object {

        fun isAce(cardNumber: CardNumber): Boolean {
            return cardNumber == CardNumber.A
        }

        const val ACE_MAXINUM = 11
        const val ACE_MINIMUM = 1
    }
}
