package blackjack.controller

import blackjack.domain.card.CardFactory
import blackjack.domain.card.Cards
import blackjack.domain.game.Game
import blackjack.domain.game.GameResult
import blackjack.domain.player.Dealer
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
        val game = Game(cards, Dealer(), gamers)

        OutputView.printCardsDealerHas(game.dealer)
        for (gamer in game.gamers.gamers) {
            OutputView.printCardsGamerHas(gamer)
        }
        return game
    }

    private fun drawPlay(game: Game) {
        for (gamer in game.gamers.gamers) {
            drawCardToGamer(gamer, game)
        }
        drawCardToDealer(game)
    }

    private fun drawCardToDealer(game: Game) {
        if (game.dealer.isDrawable()) {
            game.drawCardToDealer()
            OutputView.printDealerDrawsCard()
        }
    }

    private fun drawCardToGamer(gamer: Gamer, game: Game) {
        OutputView.printNewLine()
        while (gamer.isDrawable() && InputView.getYesOrNo(gamer)) {
            game.drawCardToPlayer(gamer)
            OutputView.printCardsGamerHas(gamer)
        }
    }

    private fun getResult(game: Game) {
        OutputView.printGameResult(game)

        val dealerResult = game.gamers.gamers.map { gamer ->
            GameResult.calculate(game.dealer, gamer).reverse()
        }.toMutableList()

        val gamerResults = game.gamers.gamers.associate { gamer ->
            gamer.name to GameResult.calculate(game.dealer, gamer)
        }.toMutableMap()

        OutputView.printFinalResult(dealerResult, gamerResults)
    }
}
