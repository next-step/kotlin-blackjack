package blackjack

import blackjack.judge.Judgement
import blackjack.state.Ready
import blackjack.state.State

class Player(
    override val name: String,
    override val state: State = Ready(PlayerDeck()),
    override val judgements: MutableList<Judgement> = mutableListOf()
) : UserRole(name, state, judgements) {
}
