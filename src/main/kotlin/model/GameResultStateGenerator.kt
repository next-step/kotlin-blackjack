package model

class GameResultStateGenerator {
    fun generate(dealer: Dealer, players: List<Participant>) {
        val dealerTotalNumber: Int = dealer.sumOfCardNumber
        var playerTotalNumber: Int
        val dealerScoreState: ScoreState = dealer.scoreState
        var playerScoreState: ScoreState

        players.forEach { player ->
            playerTotalNumber = player.sumOfCardNumber
            playerScoreState = player.scoreState
            when {
                (dealerScoreState == ScoreState.BUST) -> {
                    player.updateGameResult(GameResultState.WIN)
                }

                (playerScoreState == ScoreState.BUST) -> {
                    player.updateGameResult(GameResultState.LOSE)
                }

                (dealerScoreState == ScoreState.BLACKJACK && playerScoreState == ScoreState.BLACKJACK) -> {
                    player.updateGameResult(GameResultState.DRAW)
                }

                (dealerScoreState == ScoreState.BLACKJACK) -> {
                    player.updateGameResult(GameResultState.LOSE)
                }

                (playerScoreState == ScoreState.BLACKJACK) -> {
                    player.updateGameResult(GameResultState.WIN)
                }

                (dealerTotalNumber > playerTotalNumber) -> {
                    player.updateGameResult(GameResultState.LOSE)
                }

                (dealerTotalNumber < playerTotalNumber) -> {
                    player.updateGameResult(GameResultState.WIN)
                }
            }
        }
    }
}
