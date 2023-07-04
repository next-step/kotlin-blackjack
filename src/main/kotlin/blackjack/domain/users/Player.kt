package blackjack.domain.users

import blackjack.domain.result.PlayerResult
import blackjack.model.UserCards

class Player(
    override val userCards: UserCards,
    private var playerResult: PlayerResult = PlayerResult()
) : User {
    fun deckComplete() {
        playerResult.deckComplete()
    }

    fun isDeckComplete(): Boolean {
        return playerResult.isDeckComplete()
    }

    fun win() {
        playerResult.win()
    }

    fun isWin(): Boolean {
        return playerResult.isWin
    }
}
