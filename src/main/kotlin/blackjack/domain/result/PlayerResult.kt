package blackjack.domain.result

import blackjack.domain.player.Player

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
            return challengers.map {
                ofChallenger(
                    dealer,
                    it
                )
            }
        }

        private fun ofChallenger(dealer: Player, challenger: Player): PlayerResult {
            val gameResult = getChallengerResultWith(
                dealer,
                challenger
            )
            return PlayerResult(challenger.info.name, gameResult)
        }

        fun ofDealer(dealer: Player, challengers: List<Player>): PlayerResult {
            return challengers.map {
                getChallengerResultWith(
                    dealer,
                    it
                )
            }
                .map { if (it == GameResult.WIN) GameResult.LOSE else GameResult.WIN }
                .let { PlayerResult(dealer.info.name, it) }
        }

        // 아주 이상함
        // 어차피 전체 리팩토링 갈겨야할듯..
        private fun getChallengerResultWith(dealer: Player, challenger: Player): GameResult = when {
            !dealer.cards.isNotBusted() -> GameResult.WIN
            !challenger.cards.isNotBusted() -> GameResult.LOSE
            challenger.isBiggerScoreThan(dealer) -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }
}
