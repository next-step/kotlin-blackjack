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

        dealPlay(game)
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

    private fun dealPlay(game: Game) {
        for (gamer in game.gamers.gamers) {
            dealCardToGamer(gamer, game)
            OutputView.printCardsGamerHas(gamer)
        }
    }

    private fun dealCardToGamer(gamer: Gamer, game: Game) {
        while (InputView.getYesOrNo(gamer)) {
            game.dealCardToPlayer(gamer)
            OutputView.printCardsGamerHas(gamer)
        }
    }

    private fun getResult(game: Game) {
        println()
        for (gamer in game.gamers.gamers) {
            OutputView.printGameResult(gamer)
        }
    }
}
