package blackjack.domain.holder

import blackjack.domain.card.Card
import blackjack.domain.state.Hit
import blackjack.domain.state.State
import blackjack.domain.value.BettingAmount

data class Player(
    val name: String,
    var state: State = Hit(Hands()),
    val bettingAmount: BettingAmount = BettingAmount(0),
) {
    constructor(name: String, hands: Hands, bettingAmount: BettingAmount) : this(name, Hit(hands), bettingAmount)
    constructor(name: String, hands: Hands) : this(name, Hit(hands))

    init {
        state = state.init()
    }

    fun firstTurn(cards: Set<Card>): Player {
        state = state.draw(cards)
        return this
    }

    fun addCard(deal: Card): Player {
        state = state.draw(setOf(deal))
        return this
    }

    fun cardPoint() = state.hands.calculatePoint()
    fun blackJack() = state.hands.blackJack()
    fun flip(dealer: Dealer): Int = state.earning(dealer, bettingAmount)
    fun bust(): Boolean = state.hands.bust()
    fun firstCard(): Set<Card> = state.hands.firstCard()

}
