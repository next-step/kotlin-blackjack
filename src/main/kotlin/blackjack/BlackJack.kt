package blackjack

import blackjack.entity.CardDrawer
import blackjack.entity.Player
import blackjack.ui.GetResult
import blackjack.ui.Input

class BlackJack {
    fun play() {
        val names = Input.getNames()
        GetResult.informGameStart(names)
        println()

        val players: List<Player> = names.map { name -> Player(name, CardDrawer.drawInitialCards()) }
        players.map { player: Player -> GetResult.printPlayerStatus(player) }
        println()

        val playedPlayers = players.map { player -> checkDrawingCondition(player) }
        println()
        playedPlayers.map { player: Player -> GetResult.printPlayerStatusWithResult(player) }
    }

    fun checkDrawingCondition(player: Player): Player {
        if (!player.wallet.isAbleToDraw(player.limit)) return player
        val newPlayer = chooseDrawing(player, Input.additionalCard(player.name))
        GetResult.printPlayerStatus(newPlayer)
        return checkDrawingCondition(newPlayer)
    }

    fun chooseDrawing(player: Player, answer: String): Player{
        if (answer == "n") return player
        val newWallet = player.draw(answer)
       return Player(player.name, newWallet)
    }
}
