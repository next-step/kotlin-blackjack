package blackjack.step2.domain

object ResultManager {
    fun calculateResults(
        dealer: Dealer,
        players: List<Player>,
    ): List<GameResult> {
        val results = mutableListOf<GameResult>()
        val dealerScore = dealer.calculateScore()

        players.forEach { player ->
            val playerScore = player.calculateScore()
            val resultType = determinePlayerResult(dealerScore, playerScore)
            results.add(GameResult(player, listOf(resultType)))
        }

        val dealerWins = results.count { it.resultTypes.contains(GameResultType.LOSE) }
        val dealerLosses = results.count { it.resultTypes.contains(GameResultType.WIN) }

        // 딜러의 resultTypes 생성
        val dealerResultTypes =
            mutableListOf<GameResultType>().apply {
                repeat(dealerWins) { add(GameResultType.WIN) }
                repeat(dealerLosses) { add(GameResultType.LOSE) }
            }

        // 딜러의 결과 추가
        val dealerResult =
            GameResult(
                dealer,
                resultTypes = dealerResultTypes,
            )
        results.add(dealerResult)

        return results
    }

    private fun determinePlayerResult(
        dealerScore: Int,
        playerScore: Int,
    ): GameResultType {
        return when {
            dealerScore > ScoreCalculator.BLACKJACK_SCORE -> GameResultType.WIN // 딜러가 버스트한 경우
            playerScore > ScoreCalculator.BLACKJACK_SCORE -> GameResultType.LOSE // 플레이어가 버스트한 경우
            playerScore > dealerScore -> GameResultType.WIN
            playerScore < dealerScore -> GameResultType.LOSE
            else -> GameResultType.DRAW
        }
    }
}
