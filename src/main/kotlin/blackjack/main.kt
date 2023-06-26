package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackjackGame(
        InputView::isWantedMorePredicate,
        InputView::blackjackPlayerBettingMoney,
        InputView::playerNames,
        OutputView::printDealerMoreCard,
        OutputView::printParticipant,
        OutputView::printDealerPlayersDealingResult,
        OutputView::printBlackjackParticipantsScore,
        OutputView::printBlackjackJudgeResult,
    ).start()
}
