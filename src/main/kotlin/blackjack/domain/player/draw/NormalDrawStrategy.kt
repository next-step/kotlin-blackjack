package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class NormalDrawStrategy : DrawStrategy {
    override fun draw(deck: CardDeck, count: Int): Cards {
        return deck.draw(count)
    }
}
