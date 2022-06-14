package blackjack

import blackjack.domain.Blackjack
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    Blackjack.play(
        playerNameFetcher = { InputView.getPlayerNames() },
        playerDecisionFetcher = { playerName -> InputView.getPlayerDecision(playerName) },
        startingPlayerSummaryPrinter = { summaries -> OutputView.printStartingPlayerSummaries(summaries, Blackjack.numberOfStartingCards) },
        playerSummaryPrinter = { summary -> OutputView.printPlayerSummary(summary) },
        finalPlayerSummaryPrinter = { summaries -> OutputView.printFinalPlayerSummaries(summaries) },
    )
}
