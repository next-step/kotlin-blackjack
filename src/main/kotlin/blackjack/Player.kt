package blackjack

import blackjack.judge.Judgement
import blackjack.state.Ready
import blackjack.state.Stand
import blackjack.state.State

class Player(
    override val name: String,
    override val state: State = Ready(PlayerDeck()),
    override val judgements: MutableList<Judgement> = mutableListOf()
) : UserRole(name, state, judgements) {

    override fun draw(card: Card): UserRole {
        return Player(name, state.draw(card))
    }

    override fun stand(): UserRole {
        if (state.isFinish()) {
            return this
        }
        return Player(name, Stand(state.currentCard()))
    }

    override fun isFinish(): Boolean = state.isFinish()

    override fun isDealer(): Boolean = false
}
