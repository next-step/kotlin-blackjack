package domain.player

class Players private constructor(private val players: List<Player>) : List<Player> by players {

    companion object {
        private const val MAX_PLAYER_SIZE = 8
        private const val MIN_PLAYER_SIZE = 1
        private val PLAYERS_RANGE = IntRange(MIN_PLAYER_SIZE, MAX_PLAYER_SIZE)

        fun createPlayers(playerBetAmounts: Map<String, BetAmount>): Players {
            require(PLAYERS_RANGE.contains(playerBetAmounts.keys.size)) { "플레이어 수는 1 ~ 8명이어야 합니다." }
            val players = playerBetAmounts.map { (name, betAmount) -> Player(name = name, betAmount = betAmount) }
            return Players(players)
        }
    }
}
