package blackjack

class Hit(cards: Cards) : Running(cards) {
    override fun draw(card: Card): State {
        val newCards = cards().add(card)
        return when {
            newCards.isBust() -> Bust(newCards)
            else -> Hit(newCards)
        }
    }

    override fun stay(): State = Stay(cards())
}
