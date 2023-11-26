package blackjack.domain

class Player(
    val name: String,
    val cards: PlayerCards = PlayerCards(),
    private var _decision: PlayerDecision = PlayerDecision.HIT
) {
    val decision
        get() = _decision

    fun getCard(card: Card) {
        cards.add(card)
        if (getScore() > Score.BLACKJACK) {
            turnStand()
        }
    }

    fun getScore(): Int {
        return toScore().calculate()
    }

    fun turnStand() {
        _decision = PlayerDecision.STAND
    }

    private fun toScore(): Score {
        return Score(
            cards.values.map { it.denomination }
        )
    }
}
