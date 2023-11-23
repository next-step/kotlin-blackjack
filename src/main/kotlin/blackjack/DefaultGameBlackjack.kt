package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER

class DefaultGameBlackjack(
    private val gameCardDealer: GameCardDealer
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map { GameParticipant.Player(it, gameCardDealer.deal(GAME_INIT_CARD_SIZE)) }

        return GameParticipants(gamePlayers, GameParticipant.Dealer(cards = gameCardDealer.deal(GAME_INIT_CARD_SIZE)))
    }

    override fun continueDealing(player: GameParticipant): GameParticipant =
        player.receiveCard(gameCardDealer.deal())
}
