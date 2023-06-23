package blackjack.ui

import blackjack.domain.Game
import blackjack.domain.GameOutcomeCalculator
import blackjack.domain.Player

class GameController(
    private val gameInput: GameInput,
    private val gameOutput: GameOutput,
    private val gameOutcomeCalculator: GameOutcomeCalculator
) {

    fun play() {
        val requestNames = gameInput.requestPlayers()
        val game = Game.from(requestNames)

        game.dealInitialCards()
        gameOutput.printDealInitialCards(game.players)

        game.players.forEach {
            progressPlayer(player = it, game = game)
        }

        game.deaCardsToDealerAndTo {
            gameOutput.printDealerOneMorePick()
        }

        val gameResult = gameOutcomeCalculator.calculate(dealer = game.dealer, players = game.players)

        gameOutput.printAllDeckStatus(players = game.players, dealer = game.dealer)
        gameOutput.printOutcome(gameResult)
    }

    private fun progressPlayer(player: Player, game: Game) {
        if (player.isAddable() && isRepeatDraw(player)) {
            game.deal(player = player, capacity = 1)
            gameOutput.printDeckStatus(player = player)
            progressPlayer(player = player, game = game)
        }
    }

    private fun isRepeatDraw(player: Player): Boolean =
        gameInput.requestConfirmDrawCard(player.name).lowercase() == "y"
}
