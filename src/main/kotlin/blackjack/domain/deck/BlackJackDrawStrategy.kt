package blackjack.domain.deck

import blackjack.domain.card.Card

class BlackJackDrawStrategy : DrawStrategy {
    override fun draw(cards: MutableSet<Card>): Card {
        return cards.first().also { cards.remove(it) }
    }
}
