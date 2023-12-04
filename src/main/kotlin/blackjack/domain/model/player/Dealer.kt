package blackjack.domain.model.player

import blackjack.domain.model.Cards
import blackjack.domain.model.Name

data class Dealer(
    override val name: Name,
    override val cards: Cards,
) : AbstractPlayer() {

    override fun shouldDraw(): Boolean = !(cards.score() isHigherThan MAX_HIT_NUMBER)
    override fun getFirstOpenedCards(): Cards {
        require(cards.values.size > FIRST_OPENED_HAVE_MIN_CARD_SIZE) {
            "딜러가 처음 카드를 보여줄 경우 ${FIRST_OPENED_HAVE_MIN_CARD_SIZE}보다 많은 카드를 가지고 있어야 한다."
        }
        return Cards.from(setOf(cards.values.first()))
    }

    companion object {

        private const val DEFAULT_DEALER_NAME = "딜러"
        private const val FIRST_OPENED_HAVE_MIN_CARD_SIZE = 1
        const val MAX_HIT_NUMBER = 16

        fun of(name: Name = Name.from(DEFAULT_DEALER_NAME), cards: Cards = Cards.empty()): Dealer = Dealer(name, cards)
    }
}
