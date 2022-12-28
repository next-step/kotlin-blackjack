package blackjack.domain

class Dealer : Player("딜러") {
    override fun canDraw(): Boolean {
        return score() <= DEALERS_HIT_RULE
    }

    fun result(players: List<Player>): List<Result> {
        if (score() > 21) {
            return List(players.count()) { Result.LOSE }
        }

        val win = List(players.count { it.score() > 21 || score() > it.score() }) { Result.WIN }
        val lose = List(players.count() - win.count()) { Result.LOSE }

        return win + lose
    }

    companion object {
        const val DEALERS_HIT_RULE = 16
    }
}
