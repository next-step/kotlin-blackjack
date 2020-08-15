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

        private fun getChallengerResultWith(dealer: Player, challenger: Player): GameResult = when {
            dealer.state == State.Busted -> GameResult.WIN
            challenger.state == State.Busted -> GameResult.LOSE
            challenger.isBiggerScoreThan(dealer) -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }
}
