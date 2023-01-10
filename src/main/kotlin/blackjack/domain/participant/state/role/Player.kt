package blackjack.domain.participant.state.role

import blackjack.domain.bet.Money
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.participant.state.Name

data class Player(override val name: Name, override val state: State, override val money: Money) : Role() {
    override val isDealer: Boolean = false

    constructor(name: String, cards: PlayingCards, money: Money) : this(Name(name), initState(cards), money)

    init {
        require(state.cards.size >= NUMBER_OF_STARTING_CARDS) { "플레이어는 2장의 카드를 가지고 시작해야 합니다." }
    }
}
