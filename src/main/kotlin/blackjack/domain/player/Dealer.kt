package blackjack.domain.player

class Dealer : Gamer() {

    fun canHit(): Boolean {
        return state.canHit() &&
            cardsSize == 2 &&
            score.value <= NEED_HIT_SCORE
    }

    companion object {

        private const val NEED_HIT_SCORE = 16
    }
}
