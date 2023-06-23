package blackjack.model

class BlackjackPlayers(players: Collection<BlackjackPlayer>) {

    fun <T> map(mapper: (BlackjackPlayer) -> T): Collection<T> {
        return players.map(mapper)
    }

    val players: Collection<BlackjackPlayer> = players.toList()
}
