package game.blackjack.v2.domain.participant

class Player(name: String) : Participant(name) {
    private var _gameResultRecord: GameResult? = null
    val gameResultRecord: GameResult
        get() = _gameResultRecord
            ?: throw IllegalStateException("게임 결과를 기록해주세요.")

    fun recordResult(gameResult: GameResult) {
        _gameResultRecord = gameResult
    }
}
