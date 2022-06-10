package blackjack.view.input

import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player
import blackjack.view.input.parser.YesNoInputParser

class ConsoleHitDecisionMaker : HitDecisionMaker {

    private val yesNoInputParser = YesNoInputParser()
    override fun shouldHit(player: Player): Boolean {
        val message = "${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        return ConsoleReader.read(message, yesNoInputParser)
    }
}
