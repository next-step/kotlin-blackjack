package blackjack.controller

import blackjack.domain.BlackJackManager
import blackjack.domain.CardDeck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {

    private val cardDeck = CardDeck()
    private lateinit var blackJackManager: BlackJackManager
    const val BLACK_JACK_SCORE = 21

    fun start() {
        val playerNames = InputView.inputPlayerNames()
        val players = Players.of(
            *Players
                .getPlayerListByNames(playerNames)
                .toTypedArray()
        )

        blackJackManager = BlackJackManager(players)
        blackJackManager.giveInitialCards(cardDeck)

        OutputView.printPlayers(players)
        OutputView.printPlayersDrawnCards(players)

        hitPlayer(players)

        OutputView.printResult(players)
    }

    private fun hitPlayer(players: Players) {
        players.forEach { player ->
            while (player.canHit() && InputView.acceptMoreCard(player) == "y") {
                player.hit(cardDeck.next())
                OutputView.printPlayerDrawnCard(player)
            }
        }
    }
}
