package blackjack.controller

import blackjack.domain.card.CardFactory
import blackjack.domain.card.Cards
import blackjack.domain.game.Game
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    fun start() {
        val gamers = inputGameParticipant()
        val game = playGame(gamers)

        drawPlay(game)
        getResult(game)
    }

    private fun inputGameParticipant(): Gamers {
        OutputView.printInputGameParticipant()
        return InputView.getGamers()
    }

    private fun playGame(gamers: Gamers): Game {
        OutputView.printDivideIntoTwoPieces(gamers)

        val cards = Cards(CardFactory.defaultCards)
        val game = Game(cards, gamers)

        for (gamer in game.gamers.gamers) {
            OutputView.printCardsGamerHas(gamer)
        }
        println()
        return game
    }

    private fun drawPlay(game: Game) {
        for (gamer in game.gamers.gamers) {
            drawCardToGamer(gamer, game)
        }
    }

    private fun drawCardToGamer(gamer: Gamer, game: Game) {
        while (gamer.isDrawable() && InputView.getYesOrNo(gamer)) {
            game.drawCardToPlayer(gamer)
            OutputView.printCardsGamerHas(gamer)
        }
    }

    private fun getResult(game: Game) {
        OutputView.printGameResult(game)
    }
}
