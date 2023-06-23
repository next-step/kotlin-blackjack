package blackjack.domain.game

import blackjack.domain.card.PlayerCardDeckCapture

data class CardDistributionResult(
    val playerCardDeckCaptures: List<PlayerCardDeckCapture>,
) {

    val countOfCardDistribution = playerCardDeckCaptures.first().cards.size
    val playerNames = playerCardDeckCaptures.map { it.playerName }

    init {
        val isAllCardsSizeSame = playerCardDeckCaptures.map { it.cards.size }.distinct().size == 1
        require(isAllCardsSizeSame) {
            "all cards size is not same"
        }
    }
}
