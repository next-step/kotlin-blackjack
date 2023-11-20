package blackjack.domain

data class Cards(
    var value: MutableList<Card> = mutableListOf()
) {
    fun add(card: Card): Cards {
        value.add(card)
        return this
    }

    fun shuffle(): Cards {
        value = value.shuffled().toMutableList()
        return this
    }

    fun clear(): Cards {
        value.clear()
        return this
    }

    fun dec(): Card {
        check(value.isNotEmpty()) { "카드가 모두 소진되었습니다." }
        return value.removeFirst()
    }

    fun getFull(): Cards {
        return Cards(
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } }
                .toMutableList()
        )
    }

    companion object {
        const val INITIAL_DEAL_SIZE = 2
        const val TOTAL_SIZE = 52
    }
}
