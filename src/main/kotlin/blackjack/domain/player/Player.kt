package blackjack.domain.player

import blackjack.domain.blackjackgame.GameStatus
import blackjack.domain.card.Card
import blackjack.domain.state.Stand

class Player(
    override val userSetting: UserSetting,
    override val gameStatus: GameStatus = GameStatus()
) : UserRole(userSetting, gameStatus) {

    override fun draw(card: Card): UserRole {
        return Player(userSetting, GameStatus(state = gameStatus.state.draw(card)))
    }

    override fun stand(): UserRole {
        if (gameStatus.state.isFinish()) {
            return this
        }
        return Player(userSetting, GameStatus(state = Stand(gameStatus.state.currentCard())))
    }

    override fun isFinish(): Boolean = gameStatus.state.isFinish()

    override fun isDealer(): Boolean = false
}
