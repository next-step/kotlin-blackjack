package blackjack.domain

class BlackjackGame(input: String) {

    private val players = mutableListOf<String>()

    init {
        transformToPlayerList(input)
    }

    private fun transformToPlayerList(input: String) {
        val playerList = input.split(",", ", ")
        playerList.forEach { player ->
            players.add(player)
        }
    }

    fun getPlayers() = players
}
