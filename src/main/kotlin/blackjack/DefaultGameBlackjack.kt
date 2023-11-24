package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER

class DefaultGameBlackjack(
    private val gameCardDealer: GameCardDealer,
    private val inputOutputStrategy: InputOutputStrategy
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map { GameParticipantPlayer(it, gameCardDealer.deal(GAME_INIT_CARD_SIZE)) }

        return GameParticipants(gamePlayers, GameParticipantDealer(cards = gameCardDealer.deal(GAME_INIT_CARD_SIZE)))
    }

    override fun continueDealing(player: GameParticipantPlayer): GameParticipantPlayer =
        playerDealing(player).also {
            inputOutputStrategy.printParticipantCards(it)
        }

    override fun continueDealing(dealer: GameParticipantDealer): GameParticipantDealer =
        if (dealer.isNotAllowedDealing()) dealer
        else {
            inputOutputStrategy.printDealerDealing()
            dealer.receiveCard(gameCardDealer.deal())
        }

    private tailrec fun playerDealing(player: GameParticipantPlayer): GameParticipantPlayer {
        if (player.isNotAllowedDealing()) return player
        inputOutputStrategy.printParticipantAction(player)
        return when (ContinueDeal.of(inputOutputStrategy.getLine())) {
            ContinueDeal.YES -> playerDealing(player.receiveCard(gameCardDealer.deal()))
            ContinueDeal.MISS -> playerDealing(player)
            ContinueDeal.NO -> player
        }
    }
}
