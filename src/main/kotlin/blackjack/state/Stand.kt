package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

class Stand(private val playerDeck: PlayerDeck) : State {
    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = true

    override fun draw(card: Card): State {
        throw IllegalArgumentException("더 이상 카드를 선택할 수 없습니다")
    }
}
