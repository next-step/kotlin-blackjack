package blackjack.domain

class Dealer(
    override val cards: Cards
) : Member {

    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE

    companion object {
        private const val DRAW_LIMIT_SCORE = 17
    }
}
