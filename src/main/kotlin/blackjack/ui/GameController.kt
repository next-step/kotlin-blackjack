package blackjack.ui

import blackjack.domain.Game
import blackjack.domain.Player

class GameController(
    private val gameInput: GameInput,
    private val gameOutput: GameOutput
) {

    fun play() {
        val requestNames = gameInput.requestPlayers()
        val game = Game.from(requestNames)

        game.dealInitialCards()
        gameOutput.printDealInitialCards(game.players)

        game.players.forEach {
            progressPlayer(player = it, game = game)
        }

        gameOutput.printAllDeckStatus(game.players)
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
