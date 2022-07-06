package camp.nextstep.blackjack.game

enum class GameResult {

    WIN, LOSE, DRAW;

    companion object {

        fun List<GameResult>.reversedResults(): List<GameResult> {
            val results = mutableListOf<GameResult>()

            val winCount = this.count { it == WIN }
            val loseCount = this.count { it == LOSE }
            val drawCount = this.count { it == DRAW }

            repeat(winCount) {
                results.add(LOSE)
            }

            repeat(drawCount) {
                results.add(DRAW)
            }

            repeat(loseCount) {
                results.add(WIN)
            }

            return results
        }

        fun of(myScore: Score, otherScore: Score): GameResult {
            return if (myScore.closerThan(otherScore)) WIN
            else if (myScore.isBust() && otherScore.isNotBust()) LOSE
            else DRAW
        }
    }
}
