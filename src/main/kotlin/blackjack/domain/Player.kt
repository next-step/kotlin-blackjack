package blackjack.domain

class Player : Participant {

    override val isEnd: Boolean
        get() {
            return state == States.BLACK_JACK
        }

    constructor(name: String, cards: Set<Card>) : super(name, PlayerCards(cards))
    constructor(name: String, vararg cards: Card) : this(name, PlayerCards(cards.toSet()))

    override fun findStateByScore(score: Int): States {
        return when {
            score < Game.BLACK_JACK_SCORE -> { States.HIT }
            score > Game.BLACK_JACK_SCORE -> { States.BUST }
            else -> { States.STAY }
        }
    }

    override fun isSmallerThanMinimumDealerScore(): Boolean {
        return cards.score <= 16
    }
}
