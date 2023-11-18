package blackjack.model

import blackjack.view.InputView
import blackjack.view.OutputView

object PlayDealer {
    fun playGameWithEachPlayer(player: Player) {
        while (getMoreCardOrNot(player)) {
            player.cardDeck.addCard(CardDealer.getCard())
            OutputView.renderPlayer(player, ::println)
        }
    }

    private fun getMoreCardOrNot(player: Player) = when (InputView.askGetCardMore(player.name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
