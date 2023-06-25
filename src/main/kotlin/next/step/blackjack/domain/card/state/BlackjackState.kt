package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards

object BlackjackState : CardsState() {

    override fun next(cards: Cards): CardsState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }
}
