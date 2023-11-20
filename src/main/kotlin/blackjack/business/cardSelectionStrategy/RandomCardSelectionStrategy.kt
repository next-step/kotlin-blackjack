package blackjack.business.cardSelectionStrategy

import blackjack.business.card.Card

class RandomCardSelectionStrategy : CardSelectionStrategy {
    override fun selectCard(cards: List<Card>): Card {
        return cards.random()
    }
}
