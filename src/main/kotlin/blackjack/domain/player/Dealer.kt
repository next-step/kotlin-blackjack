package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.running.InitialHand

class Dealer(
    val name: Name = DEALER_NAME,
    gameState: GameState = InitialHand(),
) : Participant {
    override fun draw(card: Card) {
        TODO("Not yet implemented")
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
