package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Stand
import blackjack.domain.state.Started
import blackjack.domain.state.State

open class Participant(state: State = Started()) {
    var state: State = state
        protected set

    open fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun canReceiveOneMoreCard(): Boolean {
        return state is Hit
    }

    fun receiveInitialCards(cards: Cards) {
        require(cards.size == Deck.INITIAL_DEAL_SIZE) { "처음 받아야 할 카드 수는 ${Deck.INITIAL_DEAL_SIZE}장 입니다." }
        cards.values.forEach(::receiveCard)
    }

    fun turnStand() {
        state = state.stand()
    }
    fun isStand(): Boolean {
        return state is Stand
    }

    fun showCards(): Cards {
        return state.cards
    }

    fun getScore(): Score {
        return state.scoring()
    }
}
