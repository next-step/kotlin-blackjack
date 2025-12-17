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

    players.players.forEach { OutputView.printRoundResult(it) }
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
        dealer.cards.addCard(deck.drawCard())
        players.players.forEach { player ->
            player.cards.addCard(deck.drawCard())
        }
    }

    OutputView.printFirstCard(players)
    OutputView.printCardStatusOnFirstRound(dealer)
    players.players.forEach { OutputView.printCardStatusOnFirstRound(it) }
}

fun drawPlayerCards(
    players: Players,
    deck: CardDeck,
) {
    players.forEach { player ->
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
}

fun drawDealerCards(
    dealer: Dealer,
    deck: CardDeck,
) {
    if (dealer.cards.calculateScore() < 17) {
        OutputView.printDealerMustGetCard()
        dealer.cards.addCard(deck.drawCard())
    }
}
