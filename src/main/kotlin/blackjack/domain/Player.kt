package blackjack.domain

import blackjack.domain.BetMoney.Companion.ZERO_BET_MONEY
import blackjack.domain.Profit.Companion.ZERO_PROFIT

data class Player(
    private val name: String = "",
    private var cards: Cards = Cards(setOf())
) : Participant {
    var state: State = State.STAY
        private set
    var betMoney: BetMoney = ZERO_BET_MONEY
    var profit: Profit = ZERO_PROFIT
        private set

    override fun setBetMoney(amount: Int) {
        betMoney = BetMoney(amount)
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

    override fun toString(): String = name
}
