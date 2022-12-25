package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State

class Player(
    val name: String,
    val cards: Cards
) {
    val score: Int
        get() = cards.score

    private var state: State = State.of(cards)

    init {
        require(cards.size == INIT_CARD_COUNT) {
            "Player should have $INIT_CARD_COUNT cards before starting game [${cards.size}]"
        }

        require(name.isNotBlank()) {
            "Player's name should not be blank"
        }
    }

    fun hit(card: Card) {
        check(state.isAbleToHit) {
            "Player's state [${state.name}] should be able to hit"
        }

        cards.addCard(card)
        state = state.hit(cards)
    }

    fun stay() {
        check(state.isAbleToStay) {
            "Player's state [${state.name}] should be able to stay"
        }

        state = state.stay(cards)
    }

    fun isNotFinished() = !isFinished()

    fun isFinished() = state.isFinished

    override fun toString(): String {
        return "${name}카드: $cards"
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
