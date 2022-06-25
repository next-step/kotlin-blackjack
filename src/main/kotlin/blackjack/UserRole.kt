package blackjack

import blackjack.judge.Judgement
import blackjack.state.Ready
import blackjack.state.State

abstract class UserRole(
    open val name: String,
    open val state: State = Ready(PlayerDeck()),
    open val judgements: MutableList<Judgement> = mutableListOf()
) {
    val cards: List<Card>
        get() = state.currentCard()
            .cards
            .toList()

    abstract fun draw(card: Card): UserRole

    abstract fun stand(): UserRole

    abstract fun isFinish(): Boolean

    abstract fun isDealer(): Boolean

    fun getScore(): Int = state.score(state.currentCard().cards)
}
