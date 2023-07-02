package blackjack.domain.player

class PlayersBuilder(private val notify: PlayerNotify) {

    var players: MutableList<Player> = notify.inputPlayer()
    fun build(): MutableList<Player> {
        return players
    }

    fun setBettingMoney() {
        notify.inputBettingMoney(players)
    }

    companion object {
        fun of(notify: PlayerNotify) : PlayersBuilder {
            return PlayersBuilder(notify)
        }
    }
}