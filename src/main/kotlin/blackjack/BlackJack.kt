package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView
import blackjack.view.InputView.inputPlayers
import blackjack.view.ParticipantView
import blackjack.view.ResultView
import blackjack.view.ViewResolver

fun main() {
    val game = Game(
        players = inputPlayers(),
        viewResolver = ViewResolver(InputView, ParticipantView, ResultView)
    )
    game.start()
}
