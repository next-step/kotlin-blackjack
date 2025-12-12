import domain.CardDeck
import domain.Dealer
import domain.GameResult
import domain.Players
import view.InputView
import view.OutputView

fun main() {
    OutputView.printPlayerNames()
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players.of(inputPlayerNames)
    val dealer = Dealer()

    val deck = CardDeck()

    repeat(2) {
        dealer.cards.addCard(deck.drawCard())
        players.players.forEach { player ->
            player.cards.addCard(deck.drawCard())
        }
    }

    OutputView.printFirstCard(players)
    OutputView.printCardStatusOnFirstRound(dealer)
    players.players.forEach { OutputView.printCardStatusOnFirstRound(it) }

    for (player in players.players) {
        while (true) {
            OutputView.printDoYouWantCard(player)
            if (InputView.inputIsContinue()) {
                player.cards.addCard(deck.drawCard())
                OutputView.printCardStatus(player)
                if (player.cards.calculateScoreTreatAceAsOne() >= 21) {
                    break
                }
            } else {
                break
            }
        }
    }

    if (dealer.cards.calculateScore() < 17) {
        OutputView.printDealerMustGetCard()
        dealer.cards.addCard(deck.drawCard())
    }

    players.players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(dealer)
    OutputView.printFinalResult(GameResult.of(dealer, players))
}
