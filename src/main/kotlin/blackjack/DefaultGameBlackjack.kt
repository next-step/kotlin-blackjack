package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER

class DefaultGameBlackjack(
    private val gameDealer: GameDealer
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GamePlayers {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map {
                GamePlayer.of(it, gameDealer.deal(GAME_INIT_CARD_SIZE))
            }
        return GamePlayers(gamePlayers)
    }

    override fun continueDealing(player: GamePlayer): GamePlayer =
        player.receiveCard(gameDealer.deal())
}
