import model.*
import view.InputView
import view.OutputView

fun main() {
    val game = init()
    drawFirstCards(game)
    drawPlayerCards(game)
    drawDealerCards(game)

    game.players.forEach { OutputView.printRoundResult(it) }
    OutputView.printRoundResult(game.dealer)
    OutputView.printFinalResult(game.getResult())
}

fun init(): BlackJackGame {
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players.of(inputPlayerNames)
    val dealer = Dealer()

    players.forEach {
        val bet = InputView.inputBetAmount(it)
        it.betAmount += bet
    }

    val deck = CardDeck()
    return BlackJackGame(players, dealer, deck)
}

fun drawFirstCards(
    game: BlackJackGame
) {
    with(game) {
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
}

fun drawPlayerCards(
    game: BlackJackGame
) {
    with(game) {
        players.forEach { player ->
            while (true) {
                if (!InputView.inputOneMoreCard(player)) {
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
}

fun drawDealerCards(
    game: BlackJackGame
) {
    with(game) {
        while (dealer.calculateScore() <= BlackJackConstants.DEALER_DRAW_THRESHOLD) {
            OutputView.printDealerMustGetCard()
            dealer.drawCardFromDeck(deck)
        }
    }
}
