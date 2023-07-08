package baclkjack.domain.play

import baclkjack.domain.card.Cards

class Player(
    override val name: String,
    override val cards: Cards = Cards(),
    val money: Money
) : User {

    var cardDrawListener: CardDrawListener? = null

    override fun isDraw(): Boolean = cardDrawListener?.isDraw(name) == true

    fun result(user: User): Int = (ofGameState(user).earningsRate * money.value).toInt()

    fun ofGameState(user: User): GameResult {
        return when {
            this.score() > user.score() && this.blackJack() -> GameResult.BLACKJACK
            this.blackJack() && user.blackJack() -> GameResult.WIN
            user.burst() -> GameResult.WIN
            this.burst() -> GameResult.LOSE
            this.score() > user.score() -> GameResult.WIN
            this.score() < user.score() -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
