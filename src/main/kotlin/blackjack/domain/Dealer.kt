package blackjack.domain

class Dealer : Player("딜러") {
    override fun canDraw(): Boolean {
        return score() < DEALERS_HIT_RULE
    }

    companion object {
        private const val DEALERS_HIT_RULE = 17
    }
}
