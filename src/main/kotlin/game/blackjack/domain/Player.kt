package game.blackjack.domain

class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    var status: Status = Status.HIT

    val cards: List<Card>
        get() = _cards.toList()

    fun determine(response: String): Status {
        when (response) {
            "y" -> {
                status = Status.HIT
            }
            "n" -> status = Status.STAY
        }

        return status
    }

    fun receive(card: Card): Int {
        _cards.add(card)
        val score = Denomination.score(_cards.map { it.denomination })
        if (Denomination.isBust(score)) {
            status = Status.BUST
        }
        return score
    }

    fun canReceive(): Boolean = status == Status.HIT
}
