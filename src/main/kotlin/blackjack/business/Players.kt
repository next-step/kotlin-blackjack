package blackjack.business

class Players private constructor(players: List<Player>) {
    init {
        require(players.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    companion object {
        fun from(playerNames: List<String>): Players {
            return Players(playerNames.map { Player(it) })
        }
    }
}
