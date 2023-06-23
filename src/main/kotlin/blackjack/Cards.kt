package blackjack

@JvmInline
value class Cards(
    val values: List<Card> = listOf()
) {
    init {
        require(values.size == values.toSet().size) { "중복된 카드가 저장될 수 없다." }
    }

    fun addCard(card: Card): Cards {
        require(values.contains(card).not()) { "이미 존재하는 카드를 추가할 수 없다." }
        return Cards(values + card)
    }

    fun isBust() = values.sumOf { it.denomination.score.min() } > BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun of(vararg card: Card) = Cards(card.toMutableList())
    }
}
