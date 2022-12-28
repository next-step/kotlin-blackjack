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

    override fun takeResult(playerResult: PlayerResult) {
        this.playerResult = playerResult
    }
}
