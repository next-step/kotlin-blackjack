package blackjack.domain.game

import blackjack.domain.card.PlayerCards

data class CardDistributionResult(
    val playerCards: List<PlayerCards>,
) {

    val countOfCardDistribution = playerCards.first().cards.size
    val playerNames = playerCards.map { it.playerName }

    init {
        val isAllCardsSizeSame = playerCards.map { it.cards.size }.distinct().size == 1
        require(isAllCardsSizeSame) {
            "all cards size is not same"
        }
    }
}
