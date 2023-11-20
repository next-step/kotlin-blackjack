package blackjack.business.drawConditionStrategy

import blackjack.business.util.HitCommandEvaluator

class UserInputBasedDrawCondition : DrawConditionStrategy {
    override fun shouldDraw(playerName: String, getCommand: (String) -> String): Boolean {
        val input = getCommand(playerName)
        return HitCommandEvaluator.evaluate(input)
    }
}
