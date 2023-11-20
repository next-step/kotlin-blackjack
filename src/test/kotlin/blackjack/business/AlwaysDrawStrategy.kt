package blackjack.business

import blackjack.business.drawConditionStrategy.DrawConditionStrategy

class AlwaysDrawStrategy : DrawConditionStrategy {
    override fun shouldDraw(playerName: String, getCommand: (String) -> String): Boolean {
        return true
    }
}
