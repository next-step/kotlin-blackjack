package blackjack.controller

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {

    private val cardDeck = CardDeck()
    private const val INITIAL_CARD_NUM = 2
    const val BLACK_JACK_SCORE = 21

    fun start() {
        val dealer = Dealer()
        val playerNames = InputView.inputPlayerNames()
        var players = Players.from(Players.getPlayerListByNames(playerNames))
        players += dealer
        acceptInitialCards(players)

        OutputView.printPlayers(players)
        OutputView.printPlayersDrawnCards(players)

        hitPlayers(players)

        hitDealer(dealer)

        OutputView.printScoreResult(players)
        printDealerResults(players)
        printPlayersResults(players)
    }

    private fun acceptInitialCards(players: Players) {
        repeat(INITIAL_CARD_NUM) {
            players.eachAcceptCards(cardDeck)
        }
    }

    private fun hitPlayers(players: Players) {
        players.filteredExceptedDealer().forEach { player ->
            while (player.canHit() && InputView.acceptMoreCard(player)) {
                player.hit(cardDeck.next())
                OutputView.printPlayerDrawnCard(player)
            }
        }
    }

    private fun hitDealer(dealer: Dealer) {
        if (dealer.canHit()) {
            OutputView.printDealerDraw()
            dealer.hit(cardDeck.next())
        }
    }

    private fun printDealerResults(players: Players) {
        if (players.dealer != null) {
            players.dealer!!.makeDealerGameResult(players.filteredExceptedDealer())
                .also {
                    OutputView.printDealerResult(it)
                }
        }
    }

    private fun printPlayersResults(players: Players) {
        if (players.dealer != null) {
            players.filteredExceptedDealer().forEach {
                OutputView.printPlayerResult(it, it.result(players.dealer!!))
            }
        }
    }
}
