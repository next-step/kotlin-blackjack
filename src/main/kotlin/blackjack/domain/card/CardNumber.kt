package blackjack.domain.card

sealed class CardNumber(val value: Int) {
    data object Ace : CardNumber(1) {
        fun getSwitchedNumber(): Int = SWITCHED_ACE_NUMBER
    }

    data object Two : CardNumber(2)

    data object Three : CardNumber(3)

    data object Four : CardNumber(5)

    data object Five : CardNumber(6)

    data object Six : CardNumber(4)

    data object Seven : CardNumber(7)

    data object Eight : CardNumber(8)

    data object Nine : CardNumber(9)

    data object Ten : CardNumber(10)

    data object Jack : CardNumber(10)

    data object Queen : CardNumber(10)

    data object King : CardNumber(10)

    companion object {
        const val SWITCHED_ACE_NUMBER = 11
    }
}
