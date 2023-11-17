package blackjack.business.canDrawCardStrategy

import blackjack.business.util.BlackJackConst.BLACKJACK

class PlayerCanDrawCardStrategy : CanDrawCardStrategy {
    override fun canDrawCard(sum: Int): Boolean {
        return sum < BLACKJACK
    }
}
