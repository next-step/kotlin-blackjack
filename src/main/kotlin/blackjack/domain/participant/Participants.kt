package blackjack.domain.participant

class Participants(val participants: List<Participant>) {
    init {
        require(participants.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { PLAYERS_SIZE_EXCEPTION_MESSAGE }
    }

    fun findPlayer(name: String) = participants.find { it.name == name } ?: throw IllegalStateException(NOT_FOUND_PLAYER_EXCEPTION_MESSAGE)

    fun extractDealer(): Dealer = participants.filterIsInstance<Dealer>().single()

    fun extractPlayers(): List<Player> = participants.filterIsInstance<Player>()

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val PLAYERS_SIZE_EXCEPTION_MESSAGE = "블랙잭 플레이어는 1~6명 이어야 합니다."
        private const val NOT_FOUND_PLAYER_EXCEPTION_MESSAGE = "존재하지 않는 플레이어 입니다."
    }
}
