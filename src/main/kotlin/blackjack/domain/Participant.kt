package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Stand
import blackjack.domain.state.Started
import blackjack.domain.state.State

open class Participant {
    var state: State = Started()
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
        check(state is Hit) { "Hit 상태가 아닙니다." }
        state = (state as Hit).stand()
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
