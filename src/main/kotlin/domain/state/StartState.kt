package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult

class StartState private constructor(private val cards: Cards, private val betAmount: Int) : State {

    override fun draw(card: Card): State {
        val currentCards = Cards(this.getCards().plus(card))
        val betAmount = getBetAmount()
        return if (currentCards.isDrawable()) Hit(cards = currentCards, betAmount = betAmount)
        else Burst(cards = cards, betAmount = betAmount)
    }

    override fun stop(): State = Stand(cards = cards, betAmount = betAmount)

    override fun getCards(): Cards = this.cards

    override fun getPlayerGameResult(state: State): PlayerGameResult {
        throw UnsupportedOperationException("시작 상태는 지원하지 않음.")
    }

    override fun getBetAmount(): Int = betAmount

    override fun getRevenue(state: State): Int {
        throw UnsupportedOperationException("시작 상태는 지원하지 않음.")
    }

    companion object {
        fun start(cards: Cards, betAmount: Int = 0): State =
            if (cards.isBlackjack()) {
                Blackjack(cards = cards, betAmount = betAmount)
            } else {
                StartState(cards = cards, betAmount = betAmount)
            }
    }
}
