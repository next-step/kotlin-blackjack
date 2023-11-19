package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER

class DefaultGameBlackjack(
    private val gameDealer: GameDealer
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map { GameParticipant.Player(it, gameDealer.deal(GAME_INIT_CARD_SIZE)) }

        return GameParticipants(gamePlayers, GameParticipant.Dealer(cards = gameDealer.deal(GAME_INIT_CARD_SIZE)))
    }

    override fun continueDealing(player: GameParticipant): GameParticipant =
        player.receiveCard(gameDealer.deal())
}
