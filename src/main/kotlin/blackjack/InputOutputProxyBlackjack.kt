package blackjack

class InputOutputProxyBlackjack(
    private val target: GameBlackjack,
    private val inputOutputStrategy: InputOutputStrategy
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants =
        target.initialDealing(playerNames)

    override fun continueDealing(player: GameParticipant): GameParticipant {
        val updatedParticipant = when (player) {
            is GameParticipant.Dealer -> dealerDealing(player)
            is GameParticipant.Player -> playerDealing(player)
        }

        if (player is GameParticipant.Player) {
            inputOutputStrategy.printParticipantCards(updatedParticipant)
        }
        return updatedParticipant
    }

    private fun dealerDealing(participant: GameParticipant): GameParticipant {
        return if (participant.isNotAllowedDealing()) participant
        else {
            inputOutputStrategy.printDealerDealing()
            target.continueDealing(participant)
        }
    }

    private tailrec fun playerDealing(participant: GameParticipant): GameParticipant {
        if (participant.isNotAllowedDealing()) return participant
        inputOutputStrategy.printParticipantAction(participant)
        return when (ContinueDeal.of(inputOutputStrategy.getLine())) {
            ContinueDeal.YES -> playerDealing(target.continueDealing(participant))
            ContinueDeal.MISS -> playerDealing(participant)
            ContinueDeal.NO -> participant
        }
    }
}
