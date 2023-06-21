package blackjack.domain

class Cards private constructor(
    val cards: List<Card>
) {
    override fun toString(): String = cards.joinToString(", ") { it.toString() }

    fun add(newCard: Card): Cards {
        val newCards = cards.toMutableList()
        newCards.add(newCard)
        return Cards(newCards)
    }

    fun copy(): Cards = Cards(this.cards)

    fun sum(): Int {
//        if (cards.size < 2) {
//            throw IllegalArgumentException("최소 2개의 카드가 있어야 합니다.")
//        }
        //todo 구현 예정

        return cards.sumOf { it.numbers[0] }
    }

    companion object {
        const val MAX_SUM: Int = 21
        fun empty(): Cards = Cards(emptyList())
    }
}
