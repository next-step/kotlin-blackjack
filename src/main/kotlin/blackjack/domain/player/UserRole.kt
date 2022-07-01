package blackjack.domain.player

import blackjack.domain.blackjackgame.GameStatus
import blackjack.domain.card.Card

abstract class UserRole(
    open val userSetting: UserSetting,
    open val gameStatus: GameStatus = GameStatus()
) {
    val cards: List<Card>
        get() = gameStatus.getCards()
    

    abstract fun draw(card: Card): UserRole

    abstract fun stand(): UserRole

    abstract fun isFinish(): Boolean

    abstract fun isDealer(): Boolean

    fun isBlackjack(): Boolean = cards.size == 2 && getScore() == 21

    fun isWinner(): Boolean = gameStatus.isWinner()

    fun getScore(): Int = gameStatus.getScore()

    fun getBettingMoney(): Int = userSetting.bettingMoney
}
