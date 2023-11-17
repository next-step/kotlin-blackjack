package blackjack.business

import blackjack.business.drawConditionStrategy.DrawConditionStrategy

class FixDrawConditionStrategy : DrawConditionStrategy {
    override fun shouldDraw(playerName: String): Boolean {
        return true
    }
}
