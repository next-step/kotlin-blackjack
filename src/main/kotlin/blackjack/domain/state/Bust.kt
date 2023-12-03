package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Bust(
    cards: Cards
) : Finished(cards) {
    override fun receiveCard(card: Card): State {
        throw IllegalStateException("버스트되어 카드를 받을 수 없습니다.")
    }
}
