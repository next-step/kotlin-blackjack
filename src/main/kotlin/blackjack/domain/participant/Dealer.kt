package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.participant.state.Init
import blackjack.domain.participant.state.State

class Dealer(
    private val deck: Deck = Deck.release(),
) {
    private lateinit var state: State

    fun drawCard(): Card = deck.drawCard()

    fun receiveInitCards(firstCard: Card, secondCard: Card) {
        this.state = Init.receiveCard(firstCard = firstCard, secondCard = secondCard)
    }

    fun receiveCard(card: Card) {
        this.state = this.state.receiveCard(card = card)
    }

    fun isOverThenLimitScore(): Boolean =
        if (this::state.isInitialized) state.score().value >= 17
        else throw IllegalStateException("아직 초기 카드를 받지 못했습니다.")
}
