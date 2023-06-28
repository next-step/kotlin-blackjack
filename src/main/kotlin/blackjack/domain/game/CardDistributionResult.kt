package blackjack.domain.game

import blackjack.domain.gamer.DealerCard
import blackjack.domain.gamer.PlayerCards

data class CardDistributionResult(
    val dealerCards: List<DealerCard>,
    val playerCards: List<PlayerCards>,
) {

    val playerNames = playerCards.map { it.playerName }
    val distributionCardSize = dealerCards.size

    init {
        val sizeOfCardsSize = mutableSetOf(dealerCards.size)
            .apply { addAll(playerCards.map { it.cards.value.size }) }
            .size
        require(sizeOfCardsSize == 1) {
            "all cards must have the same size"
        }
    }
}
