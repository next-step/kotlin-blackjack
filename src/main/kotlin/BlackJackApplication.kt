import domain.BlackJackConstants.Companion.BLACKJACK_WIN_SCORE
import domain.BlackJackConstants.Companion.DEALER_DRAW_THRESHOLD_SCORE
import domain.BlackJackConstants.Companion.FIRST_DRAW_COUNT
import domain.CardDeck
import domain.Dealer
import domain.GameResult
import domain.Players
import view.InputView
import view.OutputView

fun main() {
    OutputView.printPlayerNames()
    val inputPlayerNames = InputView.inputPlayerNames()

    val playersMap =
        inputPlayerNames.associateWith { it ->
            OutputView.printPlayerBettingAmount(it)
            InputView.inputPlayerBettingAmounts()
        }

    val players = Players.of(playersMap)
    val dealer = Dealer()

    val deck = CardDeck()

    repeat(FIRST_DRAW_COUNT) {
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
            if (InputView.inputIsNo()) {
                break
            }
            player.cards.addCard(deck.drawCard())
            OutputView.printCardStatus(player)
            if (player.cards.calculateScore() >= BLACKJACK_WIN_SCORE) {
                break
            }
        }
    }

    if (dealer.cards.calculateScore() < DEALER_DRAW_THRESHOLD_SCORE) {
        OutputView.printDealerMustGetCard()
        dealer.cards.addCard(deck.drawCard())
    }

    players.players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(dealer)
    OutputView.printFinalResult(GameResult.of(dealer, players))
}
