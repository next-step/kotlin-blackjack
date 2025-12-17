import domain.BlackJackConstants
import domain.CardDeck
import domain.Dealer
import domain.GameResult
import domain.Players
import view.InputView
import view.OutputView

fun main() {
    val (players, dealer, deck) = init()
    drawFirstCards(players, dealer, deck)
    drawPlayerCards(players, deck)
    drawDealerCards(dealer, deck)

    players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(dealer)
    OutputView.printFinalResult(GameResult.of(dealer, players))
}

fun init(): Triple<Players, Dealer, CardDeck> {
    OutputView.printPlayerNames()
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players.of(inputPlayerNames)
    val dealer = Dealer()

    val deck = CardDeck()
    return Triple(players, dealer, deck)
}

fun drawFirstCards(
    players: Players,
    dealer: Dealer,
    deck: CardDeck,
) {
    repeat(2) {
        dealer.drawCardFromDeck(deck)
        players.forEach {
            it.drawCardFromDeck(deck)
        }
    }

    OutputView.printFirstCard(players)
    OutputView.printCardStatusOnFirstRound(dealer)
    players.forEach { OutputView.printCardStatusOnFirstRound(it) }
}

fun drawPlayerCards(
    players: Players,
    deck: CardDeck,
) {
    players.forEach { player ->
        while (true) {
            OutputView.printDoYouWantCard(player)
            if (!InputView.inputIsContinue()) {
                break
            }
            player.drawCardFromDeck(deck)
            OutputView.printCardStatus(player)
            if (player.calculateScoreTreatAceAsOne() >= BlackJackConstants.BLACK_JACK_SCORE) {
                break
            }
        }
    }
}

fun drawDealerCards(
    dealer: Dealer,
    deck: CardDeck,
) {
    if (dealer.calculateScore() <= BlackJackConstants.DEALER_DRAW_THRESHOLD) {
        OutputView.printDealerMustGetCard()
        dealer.drawCardFromDeck(deck)
    }
}
