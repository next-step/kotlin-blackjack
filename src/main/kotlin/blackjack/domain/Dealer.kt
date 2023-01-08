package blackjack.domain

class Dealer(name: String = "딜러", myCards: Cards = Cards()) : Player(name, myCards) {

    override fun canDraw(): Boolean {
        return totalScore <= MIN_DRAW_SCORE
    }

    override fun makeReport(others: List<Player>): Report {
        if (totalScore > BLACK_JACK) return Report(0, others.size)

        var win = 0
        var lose = 0
        repeat(others.size) { index ->
            val other = others[index]
            if (totalScore < other.totalScore) lose++
            if (totalScore > other.totalScore) win++
        }
        return Report(win, lose)
    }

    fun isBlackjack(): Boolean {
        return totalScore == BLACK_JACK
    }
}
