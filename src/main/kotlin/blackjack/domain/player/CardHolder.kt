package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State

abstract class CardHolder(
    val name: String,
    val cards: Cards
) {
    val score: Int
        get() = cards.score

    private var state: State = State.of(cards)

    init {
        require(name.isNotBlank()) {
            "${this.javaClass.simpleName}'s name should not be blank"
        }

        require(cards.size == INIT_CARD_COUNT) {
            "${this.javaClass.simpleName} should have $INIT_CARD_COUNT cards before starting game [${cards.size}]"
        }
    }

    fun hit(card: Card) {
        check(isNotFinished()) {
            "${this.javaClass.simpleName} is already finished."
        }

        check(state.isAbleToHit) {
            "Player's state [${state.name}] should be able to hit"
        }

        cards.addCard(card)
        state = state.hit(cards)
    }

    fun stay() {
        check(isNotFinished()) {
            "${this.javaClass.simpleName} is already finished."
        }

        check(state.isAbleToStay) {
            "${this.javaClass.simpleName}'s state [${state.name}] should be able to stay"
        }

        state = state.stay(cards)
    }

    fun isNotFinished() = !isFinished()

    fun isFinished() = state.isFinished

    companion object {
        const val INIT_CARD_COUNT: Int = 2
    }
}
