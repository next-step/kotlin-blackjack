package blackjack.domain

class PlayerCards : Cards() {

    override fun isHit(): Boolean {
        return score() < BLACK_JACK_SCORE
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
