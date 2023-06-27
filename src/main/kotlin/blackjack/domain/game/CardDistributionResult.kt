package blackjack.domain.game

import blackjack.domain.gamer.DealerCard
import blackjack.domain.gamer.PlayerCards

data class CardDistributionResult(
    val distributionCardSize: Int,
    val dealerCards: List<DealerCard>,
    val playerCards: List<PlayerCards>,
) {

    val playerNames = playerCards.map { it.playerName }

    init {
        require(playerCards.all { it.cards.size == distributionCardSize }) {
            "The size of all player cards is not the same as the size of distribution cards."
        }
        require(dealerCards.size == distributionCardSize) {
            "The size of dealer cards is not the same as the size of distribution cards."
        }
    }
}
