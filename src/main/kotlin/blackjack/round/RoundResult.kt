package blackjack.round

import blackjack.player.Player

sealed class RoundResult(
    val player: Player,
) {
    data class Success(
        val successPlayer: Player,
    ) : RoundResult(player = successPlayer)

    data class NotRemainCardInDeck(
        val failedPlayer: Player,
    ) : RoundResult(player = failedPlayer)

    data class AlreadyDrawnCard(
        val attemptedPlayer: Player,
    ) : RoundResult(player = attemptedPlayer)
}
