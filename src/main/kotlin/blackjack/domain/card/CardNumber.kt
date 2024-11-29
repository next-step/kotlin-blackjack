package blackjack.domain.card

enum class CardNumber(val value: Int) {
    Ace(1) {
        override fun toEleven(): Int = ACE_HIGH_VALUE
    },
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(10),
    Queen(10),
    King(10),
    ;

    open fun toEleven(): Int = value

    companion object {
        const val ACE_HIGH_VALUE = 10
        val NUMBER_CACHE = listOf(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
    }
}
