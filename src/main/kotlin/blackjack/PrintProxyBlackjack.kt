package blackjack

import view.Output

class PrintProxyBlackjack(
    private val target: GameBlackjack
) : GameBlackjack {

    override fun initialDealing(playerNames: String): GamePlayers =
        target.initialDealing(playerNames)

    override fun continueDealing(player: GamePlayer): GamePlayer {
        val updatedPlayer = target.continueDealing(player)
        Output.printPlayerCards(updatedPlayer)
        return updatedPlayer
    }
}
