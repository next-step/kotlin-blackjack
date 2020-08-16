package blackjack.domain.card

class Deck(
    private val drawStrategy: DrawStrategy
) {
    fun fetchCard(): Card {
        return drawStrategy.fetchCard()
    }

    fun getDealCards(): List<Card> {
        return (1..DrawStrategy.DEAL_DRAW_COUNT).map { drawStrategy.fetchCard() }
    }
}
