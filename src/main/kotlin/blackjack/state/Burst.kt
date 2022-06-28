package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerDeck

class Burst(private val playerDeck: PlayerDeck): State {
    init {
        validate(playerDeck)
    }
    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = true

    override fun draw(card: Card): State {
        throw IllegalArgumentException("카드의 합이 21 초과로 패배하셨습니다.")
    }

    private fun validate(playerDeck: PlayerDeck) {
        require(score(playerDeck) > 21) { "유효하지 않은 카드 입니다" }
    }
}
