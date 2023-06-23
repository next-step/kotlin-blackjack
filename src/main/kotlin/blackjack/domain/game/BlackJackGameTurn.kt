package blackjack.domain.game

import blackjack.domain.player.PlayerName

sealed class BlackJackGameTurn {

    data class HitAnswerWait(
        val playerName: PlayerName,
    ) : BlackJackGameTurn()
}
