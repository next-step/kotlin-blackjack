package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Hands

class Bust(override val hands: Hands) : State{
    override fun draw(cards: Set<Card>): State {
        throw IllegalStateException("더이상 카드를 받을 수 없습니다.")
    }
}
