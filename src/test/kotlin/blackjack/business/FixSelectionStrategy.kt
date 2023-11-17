package blackjack.business

class FixSelectionStrategy : CardSelectionStrategy {
    override fun selectCard(cards: List<Card>): Card {
        return cards.first()
    }
}
