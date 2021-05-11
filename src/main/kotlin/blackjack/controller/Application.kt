package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.GameStates
import blackjack.domain.Names
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = Names(InputView.playerNames())

    val game = Game(names)

    ResultView.printInitNotice(names, Game.BLACK_JACK_CARD_COUNT)

    ResultView.printAllPlayerCards(game)

    playGameWithPlayers(game)

    ResultView.printAllResult(game)
}

private fun playGameWithPlayers(game: Game) {
    while (game.state == GameStates.PLAYING) {
        val player = game.players.findOneByStatusIsPlaying()

        val answer = InputView.askIfPlayerWantToMoreCard(player.name)

        drawOrStopByAnswer(answer, game, player)

        ResultView.printPlayerCards(player.name, player.cards)
    }
}

private fun drawOrStopByAnswer(answer: Boolean, game: Game, player: Player) {
    if (answer) {
        game.draw(player)
        return
    }

    player.stop()
}
