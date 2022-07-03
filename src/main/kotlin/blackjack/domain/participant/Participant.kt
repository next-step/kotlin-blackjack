package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class Participant(
    val name: String,
    val cards: Cards = Cards()
) {
    val score: Int
        get() = cards.calculateScore()

    fun drawCard(card: Card) {
        this.cards.add(card)
    }

    fun drawCards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    abstract fun isAbleToDraw(): Boolean

    abstract fun openInitCards(): List<Card>
}

private const val DEALER_NAME = "딜러"
private const val DEALER_DRAW_STANDARD = 16

class Dealer(
    cards: Cards = Cards()
) : Participant(
    name = DEALER_NAME,
    cards,
) {
    override fun isAbleToDraw(): Boolean {
        return cards.calculateScore() <= DEALER_DRAW_STANDARD
    }

    override fun openInitCards(): List<Card> {
        return listOf(cards.value.first())
    }
}

private const val PLAYER_MAX_SCORE = 21
private const val PLAYER_OPEN_INIT_CARDS_LENGTH = 2

class Player(
    name: String,
    cards: Cards = Cards()
) : Participant(
    name,
    cards,
) {
    private var turn: Boolean = true

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈칸 혹은 여백을 허용하지 않습니다. name = $name" }
    }

    override fun isAbleToDraw(): Boolean {
        return this.turn && cards.calculateScore() < PLAYER_MAX_SCORE
    }

    override fun openInitCards(): List<Card> {
        return cards.value.subList(0, PLAYER_OPEN_INIT_CARDS_LENGTH)
    }

    fun endOwnTurn() {
        this.turn = false
    }
}
