package blackjack.business

fun interface CardSelectionStrategy {
    fun selectCard(cards: List<Card>): Card
}
