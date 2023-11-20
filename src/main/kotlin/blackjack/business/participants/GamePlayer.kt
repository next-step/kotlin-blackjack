package blackjack.business.participants

import blackjack.business.card.Card
import blackjack.business.util.BlackJackConst
import blackjack.business.util.Money

class GamePlayer(name: String, playerCards: PlayerCards = PlayerCards()) : BasePlayer(name, playerCards) {
    private var money: Money = Money(MIN_BETTING_MONEY)

    val bettingMoney: Money
        get() = money

    override fun canDrawCard(): Boolean {
        return playerCards.sum() < BlackJackConst.BLACKJACK
    }

    override fun addCard(card: Card) {
        super.addCard(card)
        if (playerCards.isBust()) {
            setBettingMoney(bettingMoney.lose())
        }
    }

    fun setBettingMoney(money: Money) {
        this.money = money
    }

    companion object {
        const val MIN_BETTING_MONEY = 0
    }
}
