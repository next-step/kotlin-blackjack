package blackjack.domain

class Player(
    val name: String,
    private var _decision: PlayerDecision = PlayerDecision.HIT
) {
    var cards = Cards.from()
        private set

    val decision
        get() = _decision

    fun getCard(card: Card) {
        cards = cards.addCard(card)
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
            cards.map { it.denomination }
        )
    }
}
