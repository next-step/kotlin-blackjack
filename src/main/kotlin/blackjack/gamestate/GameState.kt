package blackjack.gamestate

import blackjack.Card

interface GameState {

    fun draw(card: Card): GameState

    fun stay(): Stay
}