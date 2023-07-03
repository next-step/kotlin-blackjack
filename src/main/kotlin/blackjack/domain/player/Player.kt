package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.running.InitialHand

class Player(
    val name: Name,
    val money: Money,
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

    override fun competeWith(participant: Participant): Int {
        require(participant is Dealer) { "딜러는 플레이어와만 승부할 수 있다." }
        return this.gameState.profit(money, participant.gameState)
    }

    companion object {
        fun of(name: String, money: Int) = Player(Name(name), Money(money))

        fun of(name: String, money: Int, gameState: GameState) = Player(Name(name), Money(money), gameState)
    }
}
