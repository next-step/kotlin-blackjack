package blackjack

import blackjack.domain.BlackjackGame
import blackjack.view.inputPlayerNames
import blackjack.view.printFirstDrawResult

fun main() {
    val blackJackGame = BlackjackGame.from(inputPlayerNames())

    printFirstDrawResult(blackJackGame.firstDraw())
}
