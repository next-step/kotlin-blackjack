package blackjack.domain.player

import blackjack.domain.card.Cards

class Player(
    val name: String,
    val cards: Cards
) {
    init {
        require(cards.size == INIT_CARD_COUNT) {
            "Player should have $INIT_CARD_COUNT cards before starting game [${cards.size}]"
        }

        require(name.isNotBlank()) {
            "Player's name should not be blank"
        }
    }

    fun score(): Int = cards.score()

    override fun toString(): String {
        return "${name}카드: $cards"
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
