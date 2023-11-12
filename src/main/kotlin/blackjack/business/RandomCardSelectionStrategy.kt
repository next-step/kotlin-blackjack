package blackjack.business

class RandomCardSelectionStrategy : CardSelectionStrategy {
    override fun selectCard(cards: List<Card>): Card {
        return cards.random()
    }
}
