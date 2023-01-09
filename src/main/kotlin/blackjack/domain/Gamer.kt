package blackjack.domain

class Gamer(name: String, myCards: Cards = Cards()) : Player(name, myCards) {

    override fun canDraw(): Boolean {
        return totalScore < BLACK_JACK
    }

    override fun makeReport(others: List<Player>): Report {
        if (totalScore > BLACK_JACK) return Report(0, others.size)
        if (totalScore == BLACK_JACK) return Report(others.size, 0)

        var win = 0
        var lose = 0
        repeat(others.size) { index ->
            val other = others[index]
            if (totalScore < other.totalScore) lose++
            if (totalScore > other.totalScore) win++
        }
        return Report(win, lose)
    }
}
