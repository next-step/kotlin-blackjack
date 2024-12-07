package blackjack.domain

data class Players(val players: List<Player>) {
    init {
        require(players.size == PLAYER_COUNT) { "블랙잭 게임에서 플레이어는 2명이어야 합니다" }
    }

    fun toPlayerNamesString(): String {
        return players.joinToString(", ") { it.playerName.name }
    }

    companion object {
        const val PLAYER_COUNT = 2
    }
}
