package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

interface DrawStrategy {
    fun draw(deck: CardDeck, count: Int): Cards
}
