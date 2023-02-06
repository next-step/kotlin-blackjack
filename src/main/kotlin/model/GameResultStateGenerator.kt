package model

private const val BLACK_JACK = 21

class GameResultStateGenerator {
    fun generate(dealer: Dealer, players: List<Participant>) {
        val dealerTotalNumber: Int = dealer.sumOfCardNumber
        var playerTotalNumber: Int

        players.forEach { player ->
            playerTotalNumber = player.sumOfCardNumber
            when {
                (playerTotalNumber > BLACK_JACK) -> {
                    player.updateGameResult(GameResultState.LOSE)
                }

                (dealerTotalNumber > BLACK_JACK) -> {
                    player.updateGameResult(GameResultState.WIN)
                }

                (dealerTotalNumber == BLACK_JACK && playerTotalNumber == BLACK_JACK) -> {
                    player.updateGameResult(GameResultState.DRAW)
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
