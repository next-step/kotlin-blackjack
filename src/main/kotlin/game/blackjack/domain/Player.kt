package game.blackjack.domain

class Player(val name: String) {
    private val _cards: Cards = Cards()
    var status: Status = Status.HIT

    val cards: List<Card>
        get() = _cards.get()

    fun determine(response: String): Status {
        when (response) {
            "y" -> {
                status = Status.HIT
            }
            "n" -> status = Status.STAY
        }

        return status
    }

    fun receive(card: Card): Score {
        _cards.add(card)
        val score = _cards.score()
        if (score.isBust()) {
            status = Status.BUST
        }
        return score
    }

    fun canReceive(): Boolean = status == Status.HIT
}
