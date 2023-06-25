package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.InitialHand

class Player(
    val name: Name,
    gameState: GameState = InitialHand(),
) {
    var gameState: GameState = gameState
        private set

    fun draw(card: Card) {
        gameState = gameState.draw(card)
    }

    fun stay() {
        gameState = gameState.stay()
    }

    fun isBust(): Boolean = gameState.isBust()

    fun cards(): List<Card> = gameState.cards()

    fun hands(): Hands = Hands.from(this)

    fun score(): Int = gameState.score()

    companion object {
        fun from(name: String) = Player(Name(name))

        fun of(name: String, gameState: GameState) = Player(Name(name), gameState)
    }
}
