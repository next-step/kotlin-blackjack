package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

class StartState private constructor(private val cards: Cards) : State {

    override fun draw(card: Card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards)
        else Burst(cards = currentCards)
    }

    override fun stop(): State = Stand(cards = cards)

    override fun getCards(): Cards = this.cards

    override fun getPlayerGameResult(dealerState: State): PlayerGameResult {
        throw UnsupportedOperationException("시작 상태는 지원하지 않음.")
    }

    override fun getRevenueRate(dealerState: State): BigDecimal {
        throw UnsupportedOperationException("시작 상태는 지원하지 않음.")
    }

    companion object {
        fun start(cards: Cards): State =
            if (cards.isBlackjack()) {
                Blackjack(cards = cards)
            } else {
                StartState(cards = cards)
            }
    }
}
