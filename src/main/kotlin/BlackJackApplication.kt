import domain.Dealer
import domain.GameResult
import domain.Player
import domain.Players
import service.BlackjackGameService
import view.InputView
import view.OutputView

fun main() {
    val blackjackGameService = BlackjackGameService()
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players(inputPlayerNames.map { Player(it, InputView.inputPlayerMoney(it)) })
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
                if (!player.isDrawAvailable()) {
                    break
                }
            } else {
                break
            }
        }
    }

    if (dealer.isDrawAvailable()) {
        OutputView.printDealerMustGetCard()
        blackjackGameService.drawCards(dealer)
    }

    players.players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(dealer)
    OutputView.printFinalResult(GameResult.of(dealer, players))
}
