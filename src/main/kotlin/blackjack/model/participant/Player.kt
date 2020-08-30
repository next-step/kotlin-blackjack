package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.profit.Profit
import blackjack.model.state.State
import blackjack.model.state.running.Started

data class Player(
    val info: PlayerInfo,
    override val state: State = Started()
) : Participant {

    override fun takeDefaultCards(takeCards: () -> Cards): Player =
        this.copy(state = Started(takeCards()).blackJackChecked())

    override fun draw(newCard: Card): Player = this.copy(state = state.addCard(newCard))

    override fun canDraw(): Boolean = !state.isFinished

    fun stay(): Player = this.copy(state = state.stay())

    fun profit(dealerState: State): Profit {
        return Profit(
            participantName = info.name,
            profit = state.profit(dealerState, info.betMoney)
        )
    }

    override fun toString(): String = info.name
}
