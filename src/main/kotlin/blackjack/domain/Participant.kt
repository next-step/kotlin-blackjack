package blackjack.domain

abstract class Player(val name: String, val myCards: Cards = Cards()) {

    val totalScore: Int
        get() = myCards.totalScore

    abstract fun canDraw(): Boolean

    fun draw(card: Card) {
        myCards.add(card)
    }

    operator fun plus(players: List<Player>): List<Player> {
        val list = players.toMutableList()
        list.add(this)
        return list
    }
}

class Dealer(name: String = "딜러", myCards: Cards = Cards()) : Player(name, myCards) {
    override fun canDraw(): Boolean {
        return totalScore <= MIN_DRAW_SCORE
    }

    fun makeReport(others: List<Player>): Report {
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

class Gamer(name: String, myCards: Cards = Cards()) : Player(name, myCards) {
    override fun canDraw(): Boolean {
        return totalScore < BLACK_JACK
    }

    fun makeReport(others: List<Player>): Report {
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
