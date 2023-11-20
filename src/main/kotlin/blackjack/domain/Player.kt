package blackjack.domain

class Player(
    val name: String
) {
    var cards = Cards()
    var decision = PlayerDecision.HIT
        private set

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
        decision = PlayerDecision.STAND
    }

    private fun toScore(): Score {
        return Score(
            cards.value.map { it.denomination }
        )
    }
}
