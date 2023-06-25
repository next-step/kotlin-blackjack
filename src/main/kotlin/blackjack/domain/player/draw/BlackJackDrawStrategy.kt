package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class BlackJackDrawStrategy : DrawStrategy {
    override fun draw(deck: CardDeck, count: Int): Cards {
        throw IllegalStateException("블랙잭 상태에서는 카드를 뽑을 수 없습니다.")
    }
}
