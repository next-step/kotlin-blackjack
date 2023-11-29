package blackjack

import blackjack.GameBlackjack.Companion.GAME_INIT_CARD_SIZE

class DefaultGameBlackjack(
    private val gameCardDealer: GameCardDealer,
    private val inputOutputStrategy: InputOutputStrategy
) : GameBlackjack {

    override fun initialDealing(players: List<GamePlayer>): GameParticipants {
        val gamePlayers = players
            .map {
                GameParticipantPlayer(
                    it.name,
                    gameCardDealer.deal(GAME_INIT_CARD_SIZE),
                    it.betAmount
                )
            }

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
