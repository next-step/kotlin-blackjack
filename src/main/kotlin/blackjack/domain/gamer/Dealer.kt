package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Dealer : Gamer() {

    override fun canHit(): Boolean {
        return state.isHit() &&
            state.cards.value.size == Cards.INIT_CARD_SIZE &&
            state.cards.score <= NEED_HIT_SCORE
    }

    fun getFirstCard(): Card {
        return state.cards.value.first()
    }

    companion object {

        private const val NEED_HIT_SCORE = 16
    }
}
