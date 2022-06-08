package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView
import blackjack.view.InputView.inputPlayers
import blackjack.view.ParticipantView
import blackjack.view.ResultView

fun main() {
    val game = Game(inputPlayers())
    game.start(
        printInitialHand = ParticipantView::printInitialHand,
        printPlayerInfo = ParticipantView::printParticipantInfo,
        decideHitDecision = InputView::decidePlayerHitDecision,
        printDealerDrawOneCard = ParticipantView::printDealerDrawOneCard,
        printResult = ResultView::printResult
    )
}
