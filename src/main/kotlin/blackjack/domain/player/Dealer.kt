package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.running.InitialHand

class Dealer(
    gameState: GameState = InitialHand(),
) : Participant {
    var gameState: GameState = gameState
        private set

    override fun name(): String = DEALER_NAME

    override fun draw(card: Card) {
        gameState = gameState.draw(card)
        if (gameState.isBust()) {
            return
        }
        if (gameState.score() >= 17) {
            gameState = gameState.stay()
        }
    }

    override fun stay() = throw IllegalStateException("딜러는 직접 stay할 수 없다.")

    override fun isFinished(): Boolean = gameState.isFinished()

    override fun cards(): Set<Card> {
        if (gameState.isFinished().not()) {
            return setOf(gameState.cards().first())
        }
        return gameState.cards()
    }

    override fun hands(): Hands = Hands.from(this)

    override fun score(): Int {
        check(gameState.isFinished()) { "턴이 종료되기 전에는 점수를 조회할 수 없다" }
        return gameState.score()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
