package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER

class DefaultGameBlackjack(
    private val gameCardDealer: GameCardDealer
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map { GameParticipantPlayer(it, gameCardDealer.deal(GAME_INIT_CARD_SIZE)) }

        return GameParticipants(gamePlayers, GameParticipantDealer(cards = gameCardDealer.deal(GAME_INIT_CARD_SIZE)))
    }

    override fun continueDealing(player: GameParticipantPlayer): GameParticipantPlayer =
        player.receiveCard(gameCardDealer.deal())

    override fun continueDealing(dealer: GameParticipantDealer): GameParticipantDealer =
        dealer.receiveCard(gameCardDealer.deal())
}
