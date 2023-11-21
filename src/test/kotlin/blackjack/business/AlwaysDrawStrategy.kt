package blackjack.business

import blackjack.business.drawConditionStrategy.DrawConditionStrategy

class AlwaysDrawStrategy : DrawConditionStrategy {
    override fun shouldDraw(playerName: String, onAction: (String) -> String): Boolean = true
}
