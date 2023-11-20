package blackjack.business

import blackjack.business.card.Card
import blackjack.business.cardSelectionStrategy.CardSelectionStrategy

class FixSelectionStrategy : CardSelectionStrategy {
    override fun selectCard(cards: List<Card>): Card {
        return cards.first()
    }
}
