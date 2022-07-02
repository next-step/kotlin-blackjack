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
        val dealer = getDealer()
        GetResult.printAllStatus(players, dealer)

        val playedPlayers = players.map { player -> player.checkDrawingCondition(player) }
        println()
        val playedDealer = dealer.checkDrawingCondition(dealer)
        GetResult.printAllStatusWithResult(playedPlayers, playedDealer)
    }

    fun getPlayers(names: List<String>): List<Person> {
        val players: MutableList<Person> = mutableListOf()
        names.forEach { name: String -> players.add(Player(name, CardDrawer.drawInitialCards())) }
        return players.toList()
    }

    fun getDealer(): Dealer{
        return Dealer(CardDrawer.drawInitialCards())
    }
}
