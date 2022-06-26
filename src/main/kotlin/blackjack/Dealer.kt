package blackjack

import blackjack.state.Stand

class Dealer(
    override val name: String = "딜러",
    override val gameStatus: GameStatus = GameStatus()
) : UserRole(name, gameStatus) {

    override fun stand(): UserRole {
        if (gameStatus.state.isFinish()) {
            return this
        }
        return Dealer(name, GameStatus(state = Stand(gameStatus.state.currentCard())))
    }

    override fun draw(card: Card): UserRole {
        return when (isFinish()) {
            true -> this
            else -> {
                return Dealer(name, GameStatus(state = gameStatus.state.draw(card)))
            }
        }
    }

    override fun isFinish(): Boolean = gameStatus.state.isFinish() || getScore() > DEALER_PENALTY_MIN_SCORE

    override fun isDealer(): Boolean = true

    companion object {
        private const val DEALER_PENALTY_MIN_SCORE = 16
    }
}
