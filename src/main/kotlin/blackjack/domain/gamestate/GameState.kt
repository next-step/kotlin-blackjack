package blackjack.domain.gamestate

import blackjack.domain.card.Card

interface GameState {

    fun cards(): Set<Card>

    fun draw(card: Card): GameState

    fun stay(): Stay

    fun isBust(): Boolean

    fun score(): Int
}
