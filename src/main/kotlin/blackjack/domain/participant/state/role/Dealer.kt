package blackjack.domain.participant.state.role

import blackjack.domain.bet.Bet
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.participant.state.Name

data class Dealer(override val state: State) : Role() {
    override val name: Name
        get() = Name(DEALER_NAME)

    override val bet: Bet = Bet(0)

    constructor(cards: PlayingCards) : this(initState(cards))

    init {
        require(state.cards.size() >= NUMBER_OF_STARTING_CARDS) { "딜러는 2장의 카드를 가지고 시작해야 합니다." }
    }

    override fun isDealer(): Boolean {
        return true
    }

    companion object {
        const val STOP_SCORE = 16
        const val DEALER_NAME = "딜러"
    }
}
