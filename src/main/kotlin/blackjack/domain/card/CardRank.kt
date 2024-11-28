package blackjack.domain.card

sealed class CardRank(val value: Int, val displayName: String) {
    data object Ace : CardRank(1, "A") {
        fun toEleven() = 11
    }

    data object Two : CardRank(2, "2")
    data object Three : CardRank(3, "3")
    data object Four : CardRank(4, "4")
    data object Five : CardRank(5, "5")
    data object Six : CardRank(6, "6")
    data object Seven : CardRank(7, "7")
    data object Eight : CardRank(8, "8")
    data object Nine : CardRank(9, "9")
    data object Ten : CardRank(10, "10")
    data object Jack : CardRank(10, "J")
    data object Queen : CardRank(10, "Q")
    data object King : CardRank(10, "K")

    companion object {
        val all = listOf(Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
    }
}
