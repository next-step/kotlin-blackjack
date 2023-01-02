package blackjack.domain.holder

import blackjack.domain.card.Card
import blackjack.domain.state.BlackJack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Hands
import blackjack.domain.value.BettingAmount

data class Player(
    val name: String,
    val bettingAmount: BettingAmount = BettingAmount(0),
) {
    var hands: Hands = Hit(mutableSetOf())
        private set

    fun firstTurn(cards: Set<Card>): Player {
        hands = hands.draw(cards)
        return this
    }

    fun addCard(deal: Card): Player {
        hands = hands.draw(setOf(deal))
        return this
    }

    fun cardPoint() = hands.calculatePoint()
    fun blackJack() = hands is BlackJack
    fun bust(): Boolean = hands is Bust
    fun flip(dealer: Dealer): Int = hands.earning(dealer, bettingAmount)
}
