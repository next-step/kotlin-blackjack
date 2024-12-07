package blackjack.app

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })
    val deck = Deck()
    val game = Game(deck, players)

    game.start()
    ResultView.showInitialCards(players.toResultDTO())

    players.getPlayers().forEach { player ->
        while (InputView.askToHit(player.name)) {
            game.hit(player.name)
            ResultView.showPlayerCards(players.toResultDTO().find { it.name == player.name }!!)
        }
    }

    ResultView.showFinalResults(players.toResultDTO())
}
