package blackjack.domain

class GamePlayers(val value: List<GamePlayer>) {

    init {
        require(value.size >= MIN_NUMBER_PLAYERS) { "우효하지 않는 플레이어 인원 수 입니다. 최소 2명 이상 입력해주세요." }
        require(value.size <= MAX_NUMBER_PLAYERS) { "우효하지 않는 플레이어 인원 수 입니다. 최대 8명 까지 가능합니다." }
    }

    companion object {
        const val MIN_NUMBER_PLAYERS = 2
        const val MAX_NUMBER_PLAYERS = 8
    }
}
