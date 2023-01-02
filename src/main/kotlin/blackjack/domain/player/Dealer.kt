package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.player.betting.Profit
import blackjack.domain.player.betting.plus
import blackjack.domain.player.betting.unaryMinus
import blackjack.domain.player.result.DealerResult
import blackjack.domain.player.result.PlayerResult

class Dealer(
    name: String,
    cards: Cards,
    private val cardVendor: CardVendor
) : CardHolder(name, cards), CardVendor by cardVendor {

    private val results: MutableList<DealerResult> = mutableListOf()

    private var profits: MutableList<Profit> = mutableListOf()

    fun takeFinalCards() {
        while (isNotReady()) {
            hit(drawCard())
        }
    }

    fun takeResult(player: Player): PlayerResult {
        checkReady()

        val playerResult: PlayerResult = player.takeResult(cards)

        val dealerProfit: Profit = -player.getProfit()

        savePlayerResult(playerResult)

        addProfit(dealerProfit)

        return playerResult
    }

    fun isAddedCards(): Boolean = cards.size > INIT_CARD_COUNT

    fun getWinCount(): Int = results.count { it == DealerResult.WIN }

    fun getLoseCount(): Int = results.count { it == DealerResult.LOSE }

    fun getProfit(): Profit = profits.reduce(Profit::plus)

    private fun checkReady() {
        if (isNotReady()) {
            takeFinalCards()
        }
    }

    private fun isNotReady(): Boolean = !isReady()

    private fun isReady(): Boolean = score > DEALER_REQUIRED_MIN_SCORE

    private fun savePlayerResult(playerResult: PlayerResult): Unit =
        saveResult(playerResult.opposite)

    private fun saveResult(dealerResult: DealerResult) {
        results.add(dealerResult)
    }

    private fun addProfit(profit: Profit) {
        profits.add(profit)
    }

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_REQUIRED_MIN_SCORE = 16
    }
}
