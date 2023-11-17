package blackjack.business.drawConditionStrategy

import blackjack.view.HitCommandEvaluator
import blackjack.view.InputHandler

class UserInputBasedDrawCondition : DrawConditionStrategy {
    override fun shouldDraw(playerName: String): Boolean {
        val input = InputHandler.askForOneMore(playerName)
        return HitCommandEvaluator.evaluate(input)
    }
}
