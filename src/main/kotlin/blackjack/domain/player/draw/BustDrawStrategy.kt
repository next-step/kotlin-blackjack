package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class BustDrawStrategy : DrawStrategy {
    override fun draw(deck: CardDeck, count: Int): Cards {
        throw IllegalStateException("버스트 상태에세는 카드를 뽑을 수 없습니다.")
    }
}
