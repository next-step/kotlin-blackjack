package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

private const val MAX_SCORE = 21

class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    private var turn: Boolean = true
    val score: Int
        get() = cards.calculateScore()

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈칸 혹은 여백을 허용하지 않습니다. name = $name" }
    }

    fun drawCard(card: Card) {
        this.cards.add(card)
    }

    fun drawCards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun isAbleToDraw(): Boolean {
        return this.turn && cards.calculateScore() < MAX_SCORE
    }

    fun endOwnTurn() {
        this.turn = false
    }
}
