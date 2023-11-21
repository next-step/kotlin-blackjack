package blackjack.business.participants

import blackjack.business.util.BlackJackConst
import blackjack.business.util.Money

class GamePlayer(name: String, playerCards: PlayerCards = PlayerCards(), val money: Money = Money(MIN_BETTING_MONEY)) :
    Player(name, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.sum() < BlackJackConst.BLACKJACK

    companion object {
        const val MIN_BETTING_MONEY = 0
    }
}
