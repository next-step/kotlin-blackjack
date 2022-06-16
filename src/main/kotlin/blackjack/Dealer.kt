package blackjack

import blackjack.judge.Judgement
import blackjack.state.Ready
import blackjack.state.Stand
import blackjack.state.State

class Dealer(
    override val name: String = "딜러",
    override val state: State = Ready(PlayerDeck()),
    override val judgements: MutableList<Judgement> = mutableListOf()
) :
    UserRole(name, state) {

    override fun stand(): UserRole {
        if (state.isFinish()) {
            return this
        }
        return Dealer(name, Stand(state.currentCard()))
    }

    override fun draw(card: Card): UserRole {
        return when (isFinish()) {
            true -> this
            else -> {
                println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
                return Dealer(name, state.draw(card))
            }
        }
    }

    override fun isFinish(): Boolean = state.isFinish() || getScore() > DEALER_PENALTY_MIN_SCORE

    override fun isDealer(): Boolean = true

    companion object {
        private const val DEALER_PENALTY_MIN_SCORE = 16
    }
}
