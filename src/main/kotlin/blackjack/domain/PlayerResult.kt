package blackjack.domain

class PlayerResult(
    val name: String,
    gameResults: List<GameResult>
) {
    val winCount: Int = gameResults.count { it == GameResult.WIN }
    val loseCount: Int = gameResults.count { it == GameResult.LOSE }

    constructor(name: String, value: GameResult) : this(name, listOf(value))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerResult

        if (name != other.name) return false
        if (winCount != other.winCount) return false
        if (loseCount != other.loseCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + winCount
        result = 31 * result + loseCount
        return result
    }

    companion object {
        fun ofChallengers(dealer: Player, challengers: List<Player>): List<PlayerResult> {
            return challengers.map { ofChallenger(dealer, it) }
        }

        private fun ofChallenger(dealer: Player, challenger: Player): PlayerResult {
            val gameResult = getChallengerResultWith(dealer, challenger)
            return PlayerResult(challenger.name, gameResult)
        }

        fun ofDealer(dealer: Player, challengers: List<Player>): PlayerResult {
            return challengers.map { getChallengerResultWith(dealer, it) }
                .map { if (it == GameResult.WIN) GameResult.LOSE else GameResult.WIN }
                .let { PlayerResult(dealer.name, it) }
        }

        private fun getChallengerResultWith(dealer: Player, challenger: Player): GameResult {
            if (dealer.state == State.Busted) {
                return GameResult.WIN
            }
            if (challenger.state == State.Busted) {
                return GameResult.LOSE
            }
            if (challenger.isBiggerScoreThan(dealer)) {
                return GameResult.WIN
            }
            return GameResult.LOSE
        }
    }
    // 이쪽이 너무 지저분하네요.. 플레이어의 승패결과는 플레이이어 안에 넣는게 맞을까요?
    // 플레이어가 하는 일이 너무 많아져서 넣지 못했습니다. 플레이어 안에 넣는다면 어떤식으로
    // 분리해야할까요..? 더 좋은 방법이 잘 떠오르지 않습니다..
}
