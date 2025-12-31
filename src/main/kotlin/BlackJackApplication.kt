import domain.Dealer
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

    blackjackGameService.drawInitialCards(dealer, players) { OutputView.printFirstRoundCard(players, dealer) }

    players.players.forEach { player ->
        blackjackGameService.drawPlayerCards(
            player,
            { InputView.inputIsContinue(player.name) },
        ) { OutputView.printCardStatus(player) }
    }

    blackjackGameService.drawDealerCards(dealer) { OutputView.printDealerMustGetCard() }
    blackjackGameService.decideGameResult(dealer, players)

    OutputView.printRoundResult(players, dealer)
    OutputView.printFinalResult(dealer, players)
}
