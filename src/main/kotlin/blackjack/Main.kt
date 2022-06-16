package blackjack

import blackjack.domain.Blackjack
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    Blackjack.play(
        playerNameFetcher = { InputView.getPlayerNames() },
        playerDecisionFetcher = { playerName -> InputView.getPlayerDecision(playerName) },
        startingSummariesPrinter = { summaries -> OutputView.printStartingSummaries(summaries, Blackjack.numberOfStartingCards) },
        playerSummaryPrinter = { summary -> OutputView.printPlayerSummary(summary) },
        dealerSummaryPrinter = { summary -> OutputView.printDealerSummary(summary) },
        finalSummariesPrinter = { summaries -> OutputView.printFinalSummaries(summaries) },
    )
}
