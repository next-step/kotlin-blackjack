package blackjack

import view.Output

class PrintProxyBlackjack(
    private val target: GameBlackjack
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GameParticipants =
        target.initialDealing(playerNames)

    override fun continueDealing(player: GameParticipant): GameParticipant {
        val updatedParticipant = target.continueDealing(player)
        if (player is GameParticipant.Player) {
            Output.printParticipantCards(updatedParticipant)
        }
        return updatedParticipant
    }
}
