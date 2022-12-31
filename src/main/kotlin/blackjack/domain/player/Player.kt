package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.player.result.PlayerResult

class Player(
    name: String,
    cards: Cards,
) : CardHolder(name, cards) {

    private var playerResult: PlayerResult = PlayerResult.NOT_FINISHED

    val finalResult: PlayerResult
        get() = playerResult

    fun takeResult(otherCards: Cards): PlayerResult {
        val playerResult = calculateResult(otherCards)

        saveResult(playerResult)

        return playerResult
    }

    private fun calculateResult(otherCards: Cards): PlayerResult =
        PlayerResult.of(cards, otherCards)

    private fun saveResult(playerResult: PlayerResult) {
        this.playerResult = playerResult
    }
}
