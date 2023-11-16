package blackjack.domain.card

data class Deck(
    private val _cards: ArrayDeque<Card> = ArrayDeque()
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun draw(): Card = _cards.removeLastOrNull() ?: throw IllegalArgumentException("덱에 카드가 없습니다.")

    companion object {
        fun of(cards: List<Card>): Deck = Deck(ArrayDeque(cards))
    }
}
