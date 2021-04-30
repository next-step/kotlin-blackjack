package blackjack.domain

class Dealer(name: String, cards: PlayerCards) : Participant(name, cards) {

    override val isEnd: Boolean
        get() {
            return state == States.WIN
        }

    override fun findStateByScore(score: Int): States {
        return when {
            score < Game.BLACK_JACK_SCORE -> { States.HIT }
            score >= Game.BLACK_JACK_SCORE -> { States.WIN }
            else -> { States.STAY }
        }
    }

    override fun isSmallerThanMinimumDealerScore(): Boolean {
        return cards.score <= MINIMUM_DEALER_FIRST_SCORE
    }

    companion object {
        private const val MINIMUM_DEALER_FIRST_SCORE = 16
    }
}
