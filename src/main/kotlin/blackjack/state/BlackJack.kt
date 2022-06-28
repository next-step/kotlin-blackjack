package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

class BlackJack(private val playerDeck: PlayerDeck) : State {
    init {
        validate(playerDeck)
    }

    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = true

    override fun draw(card: Card): State {
        throw IllegalArgumentException("더 이상 카드를 선택할 수 없습니다")
    }

    override fun score(playerDeck: PlayerDeck): Int = BLACKJACK_NUMBER

    private fun validate(playerDeck: PlayerDeck) {
        require(playerDeck.cards.size == 2 && score(playerDeck) == BLACKJACK_NUMBER) { "블랙잭의 조건에 맞지 않습니다" }
    }
}
