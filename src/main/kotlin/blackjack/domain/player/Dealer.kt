package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.player.result.PlayerResult

class Dealer(
    name: String,
    cards: Cards,
    private val cardVendor: CardVendor
) : CardHolder(name, cards), CardVendor by cardVendor {

    private val results: MutableList<PlayerResult> = mutableListOf()

    fun takeFinalCards() {
        while (isNotReady()) {
            hit()
        }
    }

    override fun takeResult(playerResult: PlayerResult) {
        checkReady()

        results.add(playerResult)
    }

    fun result(player: Player): PlayerResult {
        checkReady()

        val playerResult = PlayerResult.of(
            playerCards = player.cards,
            dealerCards = cards
        )

        val dealerResult: PlayerResult = playerResult.opposite

        player.takeResult(playerResult)

        takeResult(dealerResult)

        return playerResult
    }

    fun isAddedCards(): Boolean = cards.size > INIT_CARD_COUNT

    fun getWinCount(): Int = results.count { it == PlayerResult.WIN }

    fun getLoseCount(): Int = results.count { it == PlayerResult.LOSE }

    private fun hit(): Unit = hit(drawCard())

    private fun checkReady() {
        if (isNotReady()) {
            takeFinalCards()
        }
    }

    private fun isReady(): Boolean = score > DEALER_REQUIRED_MIN_SCORE

    private fun isNotReady(): Boolean = !isReady()

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_REQUIRED_MIN_SCORE = 16
    }
}
