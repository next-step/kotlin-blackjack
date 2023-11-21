package blackjack.business.participants

import blackjack.business.util.BlackJackConst
import blackjack.business.util.Money

class GamePlayer(name: String, playerCards: PlayerCards = PlayerCards(), money: Money = Money(MIN_BETTING_MONEY)) :
    Player(name, playerCards) {
    private var _money: Money = money

    val money: Money
        get() = _money.copy()

    override fun canDrawCard(): Boolean = playerCards.sum() < BlackJackConst.BLACKJACK

    fun setBettingMoney(money: Money) {
        this._money = money
    }

    companion object {
        const val MIN_BETTING_MONEY = 0
    }
}
