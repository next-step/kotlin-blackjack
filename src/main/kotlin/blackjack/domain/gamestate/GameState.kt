package blackjack.domain.gamestate

import blackjack.domain.card.Card
import blackjack.domain.gamestate.finished.Stay

interface GameState {

    fun cards(): Set<Card>

    fun draw(card: Card): GameState

    fun stay(): GameState

    fun isBust(): Boolean

    fun isFinished(): Boolean

    fun score(): Int

    fun profit(money: Int, gameState: GameState): Int
}
