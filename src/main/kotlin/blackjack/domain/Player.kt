package blackjack.domain

class Player(
    val name: String,
    private var _decision: PlayerDecision = PlayerDecision.HIT
) {
    private var _cards = Cards.from()
    val cards
        get() = _cards

    val decision
        get() = _decision

    fun getCard(card: Card) {
        _cards = _cards.addCard(card)
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
            _cards.map { it.denomination }
        )
    }
}
