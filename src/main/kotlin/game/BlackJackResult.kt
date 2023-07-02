package game

import player.Player

class BlackJackResult(
    val players: List<Player>,
) {
    fun calculateTotalScore(player: Player): Int = player.hand.cards.score()
}
