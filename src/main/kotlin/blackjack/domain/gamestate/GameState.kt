package blackjack.domain.gamestate

import blackjack.domain.Card

interface GameState {

    fun cards(): List<Card>

    fun draw(card: Card): GameState

    fun stay(): Stay
}
