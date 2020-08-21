package blackjack.domain

import blackjack.domain.BetMoney.Companion.ZERO_BET_MONEY
import blackjack.domain.Profit.Companion.ZERO_PROFIT

open class Player(
    private val name: String = "",
    private var cards: Cards = Cards(setOf())
) : Participant {
    var state: State = State.STAY
        private set
    var betMoney: BetMoney = ZERO_BET_MONEY
    var profit: Profit = ZERO_PROFIT
        private set

    override fun setBetMoney(amount: Int) {
        profit = Profit(amount)
    }

    override fun draw(newCard: Card) {
        cards = Cards(cards.states + newCard)
    }

    override fun getStateBy(reply: String): State =
        State.valueOfState(reply, score(), cards.size)

    override fun isBlackJack(state: State): Boolean {
        if (State.BLACKJACK == state) {
            this.state = State.BLACKJACK
            profit = profit.earnWhenIsBlackJack()
            return true
        }
        return false
    }

    override fun isBust(state: State): Boolean {
        if (State.BUST == state) {
            this.state = State.BUST
            return true
        }
        return false
    }

    override fun score(): Int = cards.sumOfScores()

    override fun cardCount(): Int = cards.size

    override fun stateOfCards(): String = cards.toString()

    fun earnDoubleMoney() {
        profit = profit.earnAsMuchAsStake()
    }

    fun loseAll() {
        profit = profit.loseAll()
    }

    fun getPrincipal() {
        profit = profit.gainNothing()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (this.name != other.name) return false
        if (this.cards != other.cards) return false
        if (this.state != other.state) return false
        if (this.betMoney != other.betMoney) return false
        if (this.profit != other.profit) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result += cards.hashCode()
        result += state.hashCode()
        result += betMoney.hashCode()
        return 31 * result + profit.hashCode()
    }

    override fun toString(): String = name
}
