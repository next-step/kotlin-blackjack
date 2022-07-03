package blackjack.domain.participant.state

import blackjack.domain.deck.Card

sealed class Finished(cards: Cards) : AfterInit(cards) {

    override fun receiveCard(card: Card): State = throw IllegalStateException("턴이 종료되어 카드를 받을 수 없습니다.")

    override fun stay(): State = throw IllegalStateException("턴이 종료되어 stay 상태로 변경할 수 없습니다.")

    override fun isFinished(): Boolean = true
}
