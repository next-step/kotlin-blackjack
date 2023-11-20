package blackjack.business.participants

import blackjack.business.util.BlackJackConst
import blackjack.business.util.Money

class GamePlayer(name: String, playerCards: PlayerCards = PlayerCards()) : BasePlayer(name, playerCards) {
    private lateinit var money: Money

    val bettingMoney: Money
        get() = money

    override fun canDrawCard(): Boolean {
        return playerCards.sum() < BlackJackConst.BLACKJACK
    }

    fun setBettingMoney(money: Money) {
        this.money = money
    }
}
