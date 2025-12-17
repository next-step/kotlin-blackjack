import domain.BLACKJACK_SCORE
import domain.DEALER_STAND_SCORE
import domain.Dealer
import domain.GameResult
import domain.Players
import service.BlackjackGameService
import view.InputView
import view.OutputView

fun main() {
    val blackjackGameService = BlackjackGameService()
    OutputView.printPlayerNames()
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players.of(inputPlayerNames)
    val dealer = Dealer()

    blackjackGameService.drawInitialCards(dealer, players)
    OutputView.printFirstCard(players)
    OutputView.printCardStatusOnFirstRound(dealer, players)

    for (player in players.players) {
        while (true) {
            OutputView.printDoYouWantCard(player)
            if (InputView.inputIsContinue()) {
                blackjackGameService.drawCards(player)
                OutputView.printCardStatus(player)
                if (player.cards.calculateScore() >= BLACKJACK_SCORE) {
                    break
                }
            } else {
                break
            }
        }
    }

    if (dealer.cards.calculateScore() < DEALER_STAND_SCORE) {
        OutputView.printDealerMustGetCard()
        blackjackGameService.drawCards(dealer)
    }

    players.players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(dealer)
    OutputView.printFinalResult(GameResult.of(dealer, players))
}
