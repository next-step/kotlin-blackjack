package blackjack.domain.game

import blackjack.domain.player.PlayerNames
import blackjack.domain.shuffle.CardShuffler

fun blackJackGame(playerNames: PlayerNames): BlackJackGame {
    return BlackJackGame(
        shuffler = CardShuffler(),
        playerNames = playerNames
    )
}
