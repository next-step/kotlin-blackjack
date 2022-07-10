package blackjack

import blackjack.entity.*
import blackjack.ui.GetResult
import blackjack.ui.Input

class BlackJack {
    fun play() {
        val names: List<String> = Input.getNames()
        GetResult.informGameStart(names)

        val players = getPlayers(names)
        val dealer = getDealer()
        GetResult.printAllStatus(players, dealer)

        val playedPlayers = players.map { player -> choosePlayerDrawing(player) }
        println()
        val playedDealer = chooseDealerDrawing(dealer)
        GetResult.printAllStatusWithResult(playedPlayers, playedDealer)

        playedPlayers.forEach { player: Person -> Score.compare(player, playedDealer) }
        GetResult.getScoreResult()
    }

    fun getPlayers(names: List<String>): List<Person> {
        val players: MutableList<Person> = mutableListOf()
        names.forEach { name: String -> players.add(Player(name, CardDrawer.drawInitialCards())) }
        return players.toList()
    }

    fun getDealer(): Dealer {
        return Dealer(CardDrawer.drawInitialCards())
    }

    fun chooseDealerDrawing(dealer: Dealer): Dealer {
        if (!dealer.hands.isAbleToDraw(dealer.limit)) return dealer
        GetResult.addDealerSingleCard()
        println()
        return Dealer(dealer.draw(dealer.hands))
    }

    fun choosePlayerDrawing(player: Person): Person {
        if (!player.hands.isAbleToDraw(player.limit)) return player
        if (Input.additionalCard(player.name) == "n") return player
        val newPlayer = Player(player.name, player.draw(player.hands))
        GetResult.printPlayerStatus(newPlayer)
        return (choosePlayerDrawing(newPlayer))
    }
}
