package blackjack.step2.domain

class BetManager(
    private val bets: List<Bet>,
) {
    fun getBetAmount(player: Player): Int {
        val bet =
            bets.find { it.playerName == player.name }
                ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        return bet.amount
    }
}
