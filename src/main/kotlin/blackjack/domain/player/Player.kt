package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.running.InitialHand

class Player(
    val name: Name,
    gameState: GameState = InitialHand(),
) : Participant {
    var gameState: GameState = gameState
        private set

    override fun name(): String = name.value

    override fun draw(card: Card) {
        gameState = gameState.draw(card)
    }

    override fun stay() {
        gameState = gameState.stay()
    }

    override fun isFinished(): Boolean = gameState.isFinished()

    override fun cards(): Set<Card> = gameState.cards()

    override fun hands(): Hands = Hands.from(this)

    override fun score(): Int = gameState.score()

    companion object {
        fun from(name: String) = Player(Name(name))

        fun of(name: String, gameState: GameState) = Player(Name(name), gameState)
    }
}
