package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Started
import blackjack.domain.state.State

abstract class Participant(
    val name: String,
    val cards: Cards = Cards()
) {
    var state: State = Started(cards)
        protected set

    abstract fun receiveCard(card: Card)

    fun canReceiveOneMoreCard(): Boolean {
        return state is Hit
    }

    fun turnStand() {
        check(state is Hit) { "Hit 상태가 아닙니다." }
        state = (state as Hit).stand()
    }

    fun receiveInitialCards(cards: List<Card>) {
        require(cards.size == Deck.INITIAL_DEAL_SIZE) { "처음 받아야 할 카드 수는 ${Deck.INITIAL_DEAL_SIZE}장 입니다." }
        cards.forEach(::receiveCard)
    }
}
