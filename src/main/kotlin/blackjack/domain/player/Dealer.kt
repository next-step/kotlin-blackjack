package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name
import blackjack.domain.player.vo.PlayerStatus

class Dealer(cardsInHand: CardsInHand) : Player(PlayerStatus(Name("딜러")), cardsInHand) {
    val isBust: Boolean
        get() = cardsInHand.calculateScore() > Score.BLACKJACK

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
