package baclkjack.domain.play

import baclkjack.domain.card.Cards
import baclkjack.domain.card.Deck

class Player(
    override val name: String,
    override val money: Money = Money(0),
    override val cards: Cards = Cards()
) : User {

    var cardDrawListener: CardDrawListener? = null

    override fun start(deck: Deck) {
        repeat(Cards.FIRST_DRAW) {
            cards.add(deck.draw())
        }
    }

    override fun hit(deck: Deck) {
        cards.add(deck.draw())
    }

    override fun burst(): Boolean = cards.isBurst

    override fun blackJack(): Boolean = cards.isBlackJack

    override fun winNumber(): Boolean = cards.isWinningNumber

    override fun score(): Int = cards.score()

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
