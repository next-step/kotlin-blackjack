package blackjack.domain.gamestate.finished

import blackjack.domain.card.Card
import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.GameState

class Blackjack: Finished() {
    override fun cards(): Set<Card> {
        TODO("Not yet implemented")
    }

    override fun isBust(): Boolean {
        TODO("Not yet implemented")
    }

    override fun score(): Int {
        TODO("Not yet implemented")
    }

    override fun compete(gameState: GameState): Competition {
        TODO("Not yet implemented")
    }
}
