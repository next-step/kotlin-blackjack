package blackjack.domain.holder

import blackjack.domain.card.Card
import blackjack.domain.state.BlackJack
import blackjack.domain.state.Hit
import blackjack.domain.state.State
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point

data class Player(
    val name: String,
    val hands: Hands = Hands(),
    val bettingAmount: BettingAmount = BettingAmount(0),
    var state: State = Hit(hands),
) {
    //first turn
    //state : hit, stay, blackjack, bust
    fun addCard(deal: Card): Player {
        hands.addOne(deal)
        state = state.draw(setOf(deal))
        return this
    }

    fun firstTurn(cards: Set<Card>): Player {
        hands.addAll(cards)
        state = state.draw(cards)
        return this
    }

    fun cardPoint() = state.hands.calculatePoint()
    fun blackJack() = state.hands.blackJack()

    fun flip(dealer: Dealer): Int {
        return when {
            bust() -> bettingAmount.lose()
            blackJack() && !dealer.blackJack() -> bettingAmount.blackJack()
            dealer.bust() -> bettingAmount.win()
            dealer.blackJack() && !blackJack() -> bettingAmount.lose()
            cardPoint() > dealer.cardPoint() -> bettingAmount.win()
            cardPoint() < dealer.cardPoint() -> bettingAmount.lose()
            else -> bettingAmount.tie()
        }
    }

    fun bust(): Boolean = state.hands.bust()
    fun firstCard(): Set<Card> = state.hands.firstCard()

}
