package blackjack

import blackjack.entity.CardDrawer
import blackjack.entity.Dealer
import blackjack.entity.Person
import blackjack.entity.Player
import blackjack.ui.GetResult
import blackjack.ui.Input

class BlackJack {
    fun play() {
        val names: List<String> = Input.getNames()
        GetResult.informGameStart(names)

        val players = getPlayers(names)
        players.map { player: Person -> GetResult.printPlayerStatus(player) }
        println()

        val playedPlayers = players.map { player -> player.checkDrawingCondition() }
        println()
        playedPlayers.map { player: Person -> GetResult.printPlayerStatusWithResult(player) }
    }

//    fun checkDrawingCondition(player: Player): Player {
//        if (!player.wallet.isAbleToDraw(player.limit)) return player
//        val newPlayer = chooseDrawing(player, Input.additionalCard(player.name))
//        GetResult.printPlayerStatus(newPlayer)
//        return checkDrawingCondition(newPlayer)
//    }

    fun chooseDrawing(player: Player, answer: String): Player {
        if (answer == "n") return player
        val newWallet = player.draw()
        return Player(player.name, newWallet)
    }

    fun getPlayers(names: List<String>): List<Person> {
        val players: MutableList<Person> = mutableListOf()
        players.add(Dealer(wallet = CardDrawer.drawInitialCards()))
        names.forEach { name: String -> players.add(Player(name, CardDrawer.drawInitialCards())) }
        return players.toList()
    }
}
