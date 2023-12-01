package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Phase
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun startGame(): Game {
        val names = InputView.getNames()
        val game = Game(names)
        game.players.values.forEach {
            OutputView.printGameInitialization(game)
            OutputView.printPlayerAndCard(it)
        }
        return game
    }

    fun progressPlayerPhase(game: Game) {
        val phase = game.checkAndGetPhase(Phase.PlayerPhase::class)
        while (!phase.isFinish()) {
            when (InputView.getHitOrStay(phase.player.name)) {
                "y" -> phase.player.hit(game.deck)
                "n" -> phase.player.stay()
                else -> throw IllegalArgumentException("only 'y' or 'n' can be entered")
            }
            OutputView.printPlayerAndCard(phase.player)
        }
        game.moveToNextPhase()
    }

    fun progressEndPhase(game: Game) {
        val phase = game.checkAndGetPhase(Phase.EndPhase::class)
        phase.players.values.forEach {
            OutputView.printPlayerAndCardAndScore(it)
        }
    }
}
