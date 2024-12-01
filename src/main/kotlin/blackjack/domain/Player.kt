package blackjack.domain

class Player(
    val name: String,
    private val dealer: Dealer,
    private val _cardSet: MutableSet<BlackjackCard> = mutableSetOf(),
) {
    init {
        repeat(INIT_CARD_COUNT) { drawCard() }
    }

    val cardSet: Set<BlackjackCard>
        get() = _cardSet

    val score: Int
        get() = calculateScore()

    fun drawCard() {
        val card = dealer.drawCard()
        _cardSet.add(card)
    }

    private fun calculateScore(): Int {
        val scores = cardSet.map { it.number.score }.fold(listOf(0)) { accumulator, scores ->
            accumulator.flatMap { sum -> scores.map { sum + it } }
        }
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.max()
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
