package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

open class Finished(cards: Cards) : Started(cards) {
    override fun draw(card: Card): State {
        throw IllegalStateException("게임이 종료되어 더이상 카드를 뽑을 수 없습니다.")
    }
}
