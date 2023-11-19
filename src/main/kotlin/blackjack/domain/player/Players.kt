package blackjack.domain.player

data class Players(
    val allPlayers: List<Player>,
) {

    init {
        require(allPlayers.size == 2) {
            "플레이어는 두 명이어야 합니다"
        }
    }

    var playerInTurn: Player = allPlayers.first()
        private set

    val isPlayerInTurnOverMaxScore: Boolean
        get() = playerInTurn.isOverMaxScore

    val isLastTurn: Boolean
        get() = allPlayers.indexOf(playerInTurn) == allPlayers.lastIndex

    fun changePlayer() {
        require(playerInTurn == allPlayers.first()) {
            "플레이어 모두의 차례가 한 번씩 돌아갔습니다."
        }
        playerInTurn = allPlayers.last()
    }

    companion object {
        fun from(names: PlayerNames) =
            names.value.map { name -> createPlayer(name) }.let(::Players)

        private fun createPlayer(name: PlayerName) =
            Player(name)
    }
}
