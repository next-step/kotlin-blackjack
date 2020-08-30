package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.profit.Profits
import blackjack.model.state.running.Started
import blackjack.model.state.State

data class Dealer(
    override val state: State = Started()
) : Participant {

    override fun takeDefaultCards(takeCards: () -> Cards): Dealer =
        this.copy(state = Started(takeCards()).blackJackChecked())

    override fun draw(newCard: Card): Dealer = this.copy(state = state.addCard(newCard))

    override fun canDraw(): Boolean = score() < DEALER_DRAWING_CARD_CRITERIA_SCORE

    fun provideProfits(players: Players): Profits = players.getProfits(this.state)

    override fun toString(): String = DEALER_NAME

    companion object {
        const val DEALER_NAME = "딜러"
        private const val DEALER_DRAWING_CARD_CRITERIA_SCORE = 17
    }
}
