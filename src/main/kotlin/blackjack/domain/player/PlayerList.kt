package blackjack.domain.player

class PlayerList private constructor(private val playerList: List<Player>) : List<Player> by playerList {

    companion object {
        fun createPlayerList(playerNames: List<PlayerName>) = PlayerList(playerNames.map { Player(it) })
    }
}
