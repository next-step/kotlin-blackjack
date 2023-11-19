package blackjack.domain

data class Cards(
    var value: MutableList<Card>
) {
    fun shuffle(): Cards {
        value.shuffled()
        return this
    }

    fun dec(): Card {
        check(value.isNotEmpty()) { "카드가 모두 소진되었습니다." }
        return value.removeFirst()
    }

    companion object {
        const val SIZE = 52
        fun of(): Cards {
            return Cards(
                Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } }
                    .toMutableList()
            )
        }
    }
}
