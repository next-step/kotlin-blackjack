package blackjack.domain.card

private const val ADDITIONAL_ACE_SCORE = 10
private const val MAX_SCORE = 21

@JvmInline
value class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value
        get() = _value.toList()
    val size: Int
        get() = _value.size

    fun add(card: Card) {
        _value.add(card)
    }

    fun addAll(cards: List<Card>) {
        _value.addAll(cards)
    }

    fun calculateScore(): Int {
        val score = _value.sumOf { it.face.score }
        if (isMatchAdditionalAceScore(score)) {
            return score + ADDITIONAL_ACE_SCORE
        }

        return score
    }

    private fun isMatchAdditionalAceScore(score: Int) =
        _value.any { it.isAce } && score + ADDITIONAL_ACE_SCORE <= MAX_SCORE
}
