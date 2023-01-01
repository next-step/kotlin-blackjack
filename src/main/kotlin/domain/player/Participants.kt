package domain.player

class Participants(
    private var players: List<Player>
) {
    fun receiveCard(dealer: Dealer) {
        this.players.filter { it.isAvailableReceive() }.forEach {
            dealer.giveCard(it)
        }
    }

    fun allPlayers(): List<Player> = this.players.toList()

    companion object {
        fun from(names: List<String>): Participants {
            val players = names.map { name ->
                Player(name)
            }
            return Participants(players = players.toList())
        }
    }
}
