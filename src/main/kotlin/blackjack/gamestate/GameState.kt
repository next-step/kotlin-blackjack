package blackjack.gamestate

import blackjack.Card

interface GameState {

    fun cards(): List<Card>

    fun draw(card: Card): GameState

    fun stay(): Stay
}
