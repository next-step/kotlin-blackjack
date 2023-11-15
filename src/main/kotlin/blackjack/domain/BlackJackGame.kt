package blackjack.domain

import blackjack.domain.player.PlayerNames
import blackjack.domain.player.Players

class BlackJackGame(
    playerNames: PlayerNames
) {
    val players: Players = Players.from(playerNames)
}
