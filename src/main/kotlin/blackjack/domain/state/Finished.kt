package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class Finished(
    private val cards: Cards
) : State {
    override fun receiveCard(card: Card): State {
        throw IllegalStateException("카드를 받을 수 있는 상태가 아닙니다.")
    }

    override fun cards(): Cards {
        return cards
    }

    override fun stay(): State {
        throw IllegalStateException("경기가 종료되었기 때문에 상태를 변경할 수 없습니다.")
    }
}
