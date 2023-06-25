package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackjackGame(
        InputView::isWantedMorePredicate,
        InputView::names,
        OutputView::consumeBlackjackDealerMoreCardScoreLimit,
        OutputView::consumeParticipant,
        OutputView::consumePlayersCardCount,
        OutputView::blackjackParticipantsScoreConsumer,
        OutputView::blackjackJudgeConsumer,
    ).start()
}
