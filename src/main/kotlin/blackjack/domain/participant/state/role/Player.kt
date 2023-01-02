package blackjack.domain.participant.state.role

import blackjack.domain.bet.Bet
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.participant.state.Name

data class Player(override val name: Name, override val state: State, override val bet: Bet) : Role() {
    constructor(name: String, cards: PlayingCards, bet: Bet) : this(Name(name), initState(cards), bet)

    init {
        require(state.cards.size() >= NUMBER_OF_STARTING_CARDS) { "플레이어는 2장의 카드를 가지고 시작해야 합니다." }
    }

    override fun isDealer(): Boolean {
        return false
    }
}
