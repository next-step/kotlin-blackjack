package blackjack.domain

class Dealer(
    cards: Cards = Cards.empty(),
    state: State = getDealerStateFrom(cards)
) : Player(NAME, cards, state) {

    override fun getStateFrom(cards: Cards): State {
        return getDealerStateFrom(cards)
    }

    override fun copy(name: String, cards: Cards, state: State): Player {
        return Dealer(cards, state)
    }

    companion object {
        private const val NAME = "딜러"
        private const val DEALER_STOP_HIT_BOUNDARY_SCORE = 17

        private fun getDealerStateFrom(cards: Cards): State {
            val score = cards.sumScores()
            if (score > Cards.BLACK_JACK_SCORE) {
                return State.Busted
            }
            if (cards.isBlackJack()) {
                return State.BlackJack
            }
            if (score >= DEALER_STOP_HIT_BOUNDARY_SCORE) {
                return State.Stand
            }
            return State.Playing
        }
    }
}
