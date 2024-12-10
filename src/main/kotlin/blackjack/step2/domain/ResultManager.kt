package blackjack.step2.domain

object ResultManager {
    fun calculate(participants: List<Participant>): List<GameResult> {
        val dealer =
            participants.filterIsInstance<Dealer>().firstOrNull()
                ?: throw IllegalArgumentException("딜러가 리스트에 포함되어야 합니다.")
        val players = participants.filterIsInstance<Player>()

        // 각 참가자의 결과를 계산
        return participants.map { participant ->
            when (participant) {
                is Dealer -> calculateDealerResult(dealer = participant, players = players)
                is Player -> calculatePlayerResult(player = participant, dealer = dealer)
            }
        }.flatten()
    }

    private fun calculatePlayerResult(
        player: Player,
        dealer: Dealer,
    ): List<GameResult> {
        val playerScore = player.score()
        val dealerScore = dealer.score()

        val resultType =
            when {
                dealer.isBust() -> GameResultType.WIN // 딜러가 버스트하면 플레이어 승리
                playerScore > Participant.BLACKJACK_SCORE -> GameResultType.LOSE // 플레이어가 버스트한 경우
                playerScore > dealerScore -> GameResultType.WIN
                playerScore < dealerScore -> GameResultType.LOSE
                else -> GameResultType.DRAW
            }
        return listOf(GameResult(player, listOf(resultType)))
    }

    private fun calculateDealerResult(
        dealer: Dealer,
        players: List<Player>,
    ): List<GameResult> {
        return if (dealer.isBust()) {
            // 딜러가 버스트하면 모든 플레이어가 승리, 딜러는 전패
            listOf(GameResult(dealer, List(players.size) { GameResultType.LOSE }))
        } else {
            val winCount =
                players.count { it.isBust() } + players.count { it.score() < dealer.score() && !it.isBust() }
            val loseCount = players.count { it.score() > dealer.score() && !it.isBust() }
            val drawCount = players.count { it.score() == dealer.score() && !it.isBust() }
            val dealerResultTypes =
                List(winCount) { GameResultType.WIN } +
                    List(loseCount) { GameResultType.LOSE } +
                    List(drawCount) { GameResultType.DRAW }

            listOf(GameResult(dealer, dealerResultTypes))
        }
    }
}
