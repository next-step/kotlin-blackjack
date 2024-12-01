package blackjack.round

import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.player.Player
import blackjack.view.ResultView

object Round {
    fun run(
        player: Player,
        card: Card,
        deck: Deck,
    ): RoundResult {
        if (!deck.isRemainCard()) {
            return RoundResult.NotRemainCardInDeck(failedPlayer = player)
        }

        return deck
            .draw(card)
            ?.let { RoundResult.Success(successPlayer = getPlayResult(player = player, card = card)) }
            ?: RoundResult.AlreadyDrawnCard(attemptedPlayer = player)
    }

    private fun getPlayResult(
        player: Player,
        card: Card,
    ) = player
        .hitCard(card = card)
        .also { ResultView.printPlayerCard(player = it) }
}
