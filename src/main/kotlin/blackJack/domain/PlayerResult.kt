package blackJack.domain

class PlayerResult(
    val name: String,
    val winDrawLose: WinDrawLose
) {

    companion object {
        fun winOrLose(player: GamePlayer, dealer: GamePlayer): PlayerResult =
            PlayerResult(
                player.name, winDrawLose(dealer, player)
            )

        private fun winDrawLose(dealer: GamePlayer, player: GamePlayer): WinDrawLose =
            when {
                dealer.isBustPlayer() || !player.isBustPlayer() && player.getScore() > dealer.getScore() -> WinDrawLose.WIN
                player.getScore() == dealer.getScore() -> WinDrawLose.DRAW
                else -> WinDrawLose.LOSE
            }
    }
}
