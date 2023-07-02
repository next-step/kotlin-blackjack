package blackjack.domain.player

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

    override fun score(): Int = gameState.score()

    override fun competeWith(participant: Participant) = throw IllegalStateException("플레이어는 직접 승부할 수 없다.")

    companion object {
        fun from(name: String) = Player(Name(name))

        fun of(name: String, gameState: GameState) = Player(Name(name), gameState)
    }
}
