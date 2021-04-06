package blackjack.controller

import blackjack.domain.Game
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
    game.players.forEach {
        playGame(it, game)
    }
}

private fun playGame(it: Player, game: Game) {
    while (it.isPlaying) {
        val answer = InputView.askIfPlayerWantToMoreCard(it.name)

        drawOfStopByAnswer(answer, game, it)

        ResultView.printPlayerCards(it.name, it.cards)
    }
}

private fun drawOfStopByAnswer(answer: Boolean, game: Game, it: Player) {
    if (answer) {
        game.draw(it)
        return
    }

    it.stop()
}
