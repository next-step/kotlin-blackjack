package blackjack.domain.player

import blackjack.domain.blackjackgame.GameStatus
import blackjack.domain.card.Card
import blackjack.state.Stand

class Dealer(
    override val userSetting: UserSetting = UserSetting("딜러"),
    override val gameStatus: GameStatus = GameStatus()
) : UserRole(userSetting, gameStatus) {

    override fun stand(): UserRole {
        if (gameStatus.state.isFinish()) {
            return this
        }
        return Dealer(userSetting, GameStatus(state = Stand(gameStatus.state.currentCard())))
    }

    override fun draw(card: Card): UserRole {
        return when (isFinish()) {
            true -> this
            else -> {
                return Dealer(userSetting, GameStatus(state = gameStatus.state.draw(card)))
            }
        }
    }

    override fun isFinish(): Boolean = gameStatus.state.isFinish() || getScore() > DEALER_PENALTY_MIN_SCORE

    override fun isDealer(): Boolean = true

    companion object {
        private const val DEALER_PENALTY_MIN_SCORE = 16
    }
}
