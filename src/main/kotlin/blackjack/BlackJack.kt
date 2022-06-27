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

        val playedPlayers = players.map { player -> chooseDrawing(player) }
        println()
        playedPlayers.map { player: Player -> GetResult.printPlayerStatusWithResult(player) }
    }

    fun chooseDrawing(player: Player): Player {
        if (!player.wallet.isAbleToDraw(player.limit)) return player
        val answer: String = Input.additionalCard(player.name)
        if (answer == "n") return player
        val newWallet = player.draw(answer)
        val newPlayer = Player(player.name, newWallet)
        GetResult.printPlayerStatus(newPlayer)
        return chooseDrawing(newPlayer)
    }
}
