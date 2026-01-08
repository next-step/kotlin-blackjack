import model.BlackJackConstants
import model.BlackJackGame
import model.CardDeck
import model.Dealer
import model.Players
import view.InputView
import view.InputView.tryUntilSuccess
import view.OutputView

fun main() {
    val game = init()
    drawFirstCards(game)
    drawPlayerCards(game)
    drawDealerCards(game)

    OutputView.printRoundResult(game.dealer)
    game.players.forEach { OutputView.printRoundResult(it) }
    println()

    OutputView.printFinalResult(game.getResult())
}

fun init(): BlackJackGame {
    val players: Players =
        tryUntilSuccess {
            Players.of(InputView.inputPlayerNames())
        }
    val dealer = Dealer()

    players.forEach {
        it.betAmount = tryUntilSuccess { InputView.inputBetAmount(it) }
    }

    val deck = CardDeck()
    return BlackJackGame(players, dealer, deck)
}

fun drawFirstCards(game: BlackJackGame) {
    game.run {
        repeat(2) {
            dealer.drawCardFromDeck(deck)
            players.forEach {
                it.drawCardFromDeck(deck)
            }
        }

        OutputView.printFirstCard(players)
        OutputView.printCardStatusOnFirstRound(dealer)
        players.forEach { OutputView.printCardStatusOnFirstRound(it) }
        println()
    }
}

fun drawPlayerCards(game: BlackJackGame) {
    game.run {
        players.forEach { player ->
            while (true) {
                val isOneMoreCard = tryUntilSuccess { InputView.inputOneMoreCard(player) }
                if (!isOneMoreCard) {
                    break
                }
                player.drawCardFromDeck(deck)
                OutputView.printCardStatus(player)
                if (player.calculateScoreTreatAceAsOne() >= BlackJackConstants.BLACK_JACK_SCORE) {
                    break
                }
            }
        }
        println()
    }
}

fun drawDealerCards(game: BlackJackGame) {
    game.run {
        while (dealer.calculateScore() <= BlackJackConstants.DEALER_DRAW_THRESHOLD) {
            OutputView.printDealerMustGetCard()
            dealer.drawCardFromDeck(deck)
        }
        println()
    }
}
