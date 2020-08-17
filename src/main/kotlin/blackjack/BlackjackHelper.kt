package blackjack

import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import blackjack.model.player.Player
import blackjack.model.player.Players

fun getPlayers(dealer: Dealer, gamers: List<Gamer>): Players {
    val players = mutableListOf<Player>()

    players.add(dealer)
    players.addAll(gamers)

    return Players(players)
}

fun isGameDone(players: Players): Boolean {
    return players.checkGameDone()
}
