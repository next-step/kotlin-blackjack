package blackjack.business

import blackjack.business.BlackJackConst.BLACKJACK

class PlayerCanDrawCardStrategy : CanDrawCardStrategy {
    override fun canDrawCard(sum: Int): Boolean {
        return sum < BLACKJACK
    }
}
