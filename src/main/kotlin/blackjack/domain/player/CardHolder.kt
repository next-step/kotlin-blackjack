package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.result.PlayerResult
import blackjack.domain.state.State

abstract class CardHolder(
    val name: String,
    val cards: Cards
) {
    val score: Int
        get() = cards.score

    var state: State = State.of(cards)

    init {
        require(name.isNotBlank()) {
            "Player's name should not be blank"
        }

        require(cards.size == INIT_CARD_COUNT) {
            "CardHolder should have $INIT_CARD_COUNT cards before starting game [${cards.size}]"
        }
    }

    abstract fun takeResult(playerResult: PlayerResult)

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

    companion object {
        const val INIT_CARD_COUNT: Int = 2
    }
}
