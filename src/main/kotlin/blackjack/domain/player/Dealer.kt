package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name

class Dealer(cardsInHand: CardsInHand) : Player(Name("딜러"), cardsInHand) {

    override fun isStay(): Boolean = cardsInHand.calculateScore() > DEALER_SCORE

    override fun ready(cardDeck: CardDeck) {
        super.ready(cardDeck)

        if (cardsInHand.calculateScore() <= DEALER_SCORE) {
            cardsInHand.add(cardDeck.draw())
        }
    }

    companion object {
        private val DEALER_SCORE = Score.of(16)

        fun sit(): Dealer = Dealer(CardsInHand(emptyList()))
    }
}
