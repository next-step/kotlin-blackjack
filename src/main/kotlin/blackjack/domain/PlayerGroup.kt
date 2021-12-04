package blackjack.domain

class PlayerGroup(val players: List<Player>) {
    val dealer = Dealer()

    constructor(vararg player: Player) : this(player.toList())

    init {
        require(players.size >= MINIMUM_NUMBER_OF_PLAYERS) { NOT_ENOUGH_PLAYERS_ERROR_MSG }
        require(players.distinctBy { it.name } == this.players) { DUPLICATE_PLAYERS_ERROR_MSG }
        require(players.count { it is Dealer } == 0 ) { DEALER_IN_PLAYERS_ERROR_MSG }
    }

    fun getWinners(): List<Player> {
        val allPlayers = (players + listOf(dealer))
        val maxScore = allPlayers.filter { !it.isBusted }.maxOf { it.getTotalScore() }
        return allPlayers.filter { it.getTotalScore() == maxScore }
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val NOT_ENOUGH_PLAYERS_ERROR_MSG = "플레이어의 수가 부족합니다."
        private const val DUPLICATE_PLAYERS_ERROR_MSG = "플레이어의 이름은 중복될 수 없습니다."
        private const val DEALER_IN_PLAYERS_ERROR_MSG = "딜러는 플레이어로 참여할 수 없습니다."
    }
}
