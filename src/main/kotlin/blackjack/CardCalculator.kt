package blackjack

class CardCalculator(private val cards: List<Card>) {
    fun sum() = cards.sumOf { it.number.value }
}
