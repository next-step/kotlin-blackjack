package blackjack.domain.player

import blackjack.domain.Action

data class Players(
    val value: List<Player>,
) {

    init {
        require(value.size == PLAYER_COUNT) {
            "플레이어는 ${PLAYER_COUNT}명이어야 합니다"
        }
    }

    var inTurn: Player = value.first()
        private set

    val isLastTurn: Boolean
        get() = value.indexOf(inTurn) == value.lastIndex

    fun changePlayer() {
        require(inTurn == value.first()) {
            "플레이어 모두의 차례가 한 번씩 돌아갔습니다."
        }
        inTurn = value.last()
    }

    companion object {
        private const val PLAYER_COUNT = 2
        fun of(
            names: PlayerNames,
            desiredAction: (player: Player) -> Action,
        ) = Players(names.value.map { name -> createPlayer(name, desiredAction) })

        private fun createPlayer(name: PlayerName, desiredAction: (player: Player) -> Action) =
            Player(name, desiredAction)
    }
}
