package blackjack

class InputOutputProxyBlackjack(
    private val target: GameBlackjack,
    private val inputOutputStrategy: InputOutputStrategy
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants =
        target.initialDealing(playerNames)

    override fun continueDealing(player: GameParticipantPlayer): GameParticipantPlayer =
        playerDealing(player).also {
            inputOutputStrategy.printParticipantCards(it)
        }

    override fun continueDealing(dealer: GameParticipantDealer): GameParticipantDealer =
        if (dealer.isNotAllowedDealing()) dealer
        else {
            inputOutputStrategy.printDealerDealing()
            target.continueDealing(dealer)
        }

    private tailrec fun playerDealing(player: GameParticipantPlayer): GameParticipantPlayer {
        if (player.isNotAllowedDealing()) return player
        inputOutputStrategy.printParticipantAction(player)
        return when (ContinueDeal.of(inputOutputStrategy.getLine())) {
            ContinueDeal.YES -> playerDealing(target.continueDealing(player))
            ContinueDeal.MISS -> playerDealing(player)
            ContinueDeal.NO -> player
        }
    }
}
