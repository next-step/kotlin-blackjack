package blackjack.business

import blackjack.business.card.Card
import blackjack.business.cardSelectionStrategy.CardSelectionStrategy

class FirstCardSelectionStrategy : CardSelectionStrategy {
    override fun selectCard(cards: List<Card>): Card = cards.first()
}
