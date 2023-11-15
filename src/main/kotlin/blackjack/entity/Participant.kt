package blackjack.entity

data class Participant(
    val name: String,
    var cards: Cards,
) {
    val canGetCard: Boolean
        get() {
            val sumOfCards = cards.cards.sumOf { it.number.number }
            return sumOfCards < MAX_OF_SUM_CARDS
        }

    override fun toString(): String {
        val cardString = cards.joinToString { card -> card.toString() }
        val resultString = "결과: ${cards.sumOf { it.number.number }}"
        return "$name 카드: $cardString - $resultString"
    }

    companion object {
        private const val MAX_OF_SUM_CARDS = 21
    }
}
