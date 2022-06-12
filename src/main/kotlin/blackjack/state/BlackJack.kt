package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

class BlackJack(private val playerDeck: PlayerDeck) : State {
    init {
//        validate(playerDeck.cards)
    }

    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = true

    override fun draw(card: Card): State {
        throw IllegalArgumentException("더 이상 카드를 선택할 수 없습니다")
    }

    override fun score(cards: List<Card>): Int = BLACKJACK_NUMBER

    private fun validate(cards: List<Card>) {
        require(cards.size == 2 && score(cards) == BLACKJACK_NUMBER) { "블랙잭의 조건에 맞지 않습니다" }
    }
}
