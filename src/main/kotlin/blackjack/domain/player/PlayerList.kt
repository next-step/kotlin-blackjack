package blackjack.domain.player

class PlayerList private constructor(private val playerList: List<Player>, val dealer: Dealer = Dealer()) {

    fun getList() = playerList.toList()
    fun getSize() = playerList.size

    companion object {
        fun createPlayerList(playerNames: List<PlayerName>) = createPlayerList(playerNames, Dealer())

        fun createPlayerList(playerNames: List<PlayerName>, dealer: Dealer) =
            PlayerList(playerNames.map { Player(it) }, dealer)
    }
}
