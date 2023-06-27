package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.running.InitialHand

class Dealer(
    val name: Name = DEALER_NAME,
    gameState: GameState = InitialHand(),
) : Participant {
    var gameState: GameState = gameState
        private set

    override fun draw(card: Card) {
        gameState = gameState.draw(card)
        if (gameState.isBust()) {
            return
        }
        if (gameState.score() >= 17) {
            gameState = gameState.stay()
        }
    }

    override fun stay() {
        TODO("Not yet implemented")
    }

    override fun isBust(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cards(): Set<Card> {
        TODO("Not yet implemented")
    }

    override fun hands(): Hands {
        TODO("Not yet implemented")
    }

    override fun score(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private val DEALER_NAME = Name("딜러")
    }
}
