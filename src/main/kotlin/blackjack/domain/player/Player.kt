package blackjack.domain.player

import blackjack.GameStatus
import blackjack.domain.card.Card
import blackjack.state.Stand

class Player(
    override val name: String,
    override val gameStatus: GameStatus = GameStatus()
) : UserRole(name, gameStatus) {

    override fun draw(card: Card): UserRole {
        return Player(name, GameStatus(state = gameStatus.state.draw(card)))
    }

    override fun stand(): UserRole {
        if (gameStatus.state.isFinish()) {
            return this
        }
        return Player(name, GameStatus(state = Stand(gameStatus.state.currentCard())))
    }

    override fun isFinish(): Boolean = gameStatus.state.isFinish()

    override fun isDealer(): Boolean = false
}
