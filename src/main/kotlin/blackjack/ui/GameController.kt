package blackjack.ui

import blackjack.domain.Game
import blackjack.domain.Player

class GameController(
    private val gameInput: GameInput,
    private val gameOutput: GameOutput
) {

    fun play() {
        val requestPlayers = gameInput.requestPlayers()
        val game = Game.from(requestPlayers)

        game.dealInitialCards()
        gameOutput.printDealInitialCards(game.players)

        game.players.forEach {
            progressPlayer(player = it, game = game)
        }

        game.dealCardsToDealerAndTo {
            gameOutput.printDealerOneMorePick()
        }

        val gameResult = game.dealer.calculate(players = game.players)

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
