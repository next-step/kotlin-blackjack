package blackjack.domain

class PlayersResult(val dealer: Player, val players: Players) {

    fun getDealerResult(): Map<GameResult, Int> = players.list.groupingBy { dealer.compareTo(it) }.eachCount()

    fun getGamePlayersResult(): Map<Player, GameResult> = players.list.associateWith { it.compareTo(dealer) }
}
