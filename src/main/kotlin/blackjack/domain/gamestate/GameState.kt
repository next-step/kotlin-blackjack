package blackjack.domain.gamestate

import blackjack.domain.card.Card
import blackjack.domain.gamestate.finished.Stay

interface GameState {

    fun cards(): Set<Card>

    fun draw(card: Card): GameState

    fun stay(): Stay

    fun isBust(): Boolean

    fun isFinished(): Boolean

    fun score(): Int

    fun compete(gameState: GameState): Competition
}
