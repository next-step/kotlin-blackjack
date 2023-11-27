package blackjack.domain.component

import blackjack.domain.model.PlayerName

class BlackjackGameProxy(
    private var blackjackGame: BlackjackGame? = null
) {
    fun init(playerNames: List<PlayerName>) {
        blackjackGame = BlackjackGame.create(playerNames)
    }
}
