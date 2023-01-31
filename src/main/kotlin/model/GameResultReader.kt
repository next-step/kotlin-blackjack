package model

class GameResultReader {
    fun decideResult(dealer: Dealer, players: List<Participant>) {
        val dealerTotalNumber: Int = dealer.sumOfCardNumber
        var playerTotalNumber: Int

        players.forEach { player ->
            playerTotalNumber = player.sumOfCardNumber
            if (dealerTotalNumber > BLACK_JACK) {
                player.updateGameResultState(GameResultState.WIN)
                dealer.updateGameResultState(GameResultState.LOSE)
                return@forEach
            }
            if (playerTotalNumber > BLACK_JACK) {
                player.updateGameResultState(GameResultState.LOSE)
                dealer.updateGameResultState(GameResultState.WIN)
                return@forEach
            }
            if (dealerTotalNumber == BLACK_JACK && playerTotalNumber == BLACK_JACK) {
                player.updateGameResultState(GameResultState.DRAW)
                dealer.updateGameResultState(GameResultState.DRAW)
                return@forEach
            }
            if (dealerTotalNumber > playerTotalNumber) {
                player.updateGameResultState(GameResultState.LOSE)
                dealer.updateGameResultState(GameResultState.WIN)
                return@forEach
            }
            if (dealerTotalNumber < playerTotalNumber) {
                player.updateGameResultState(GameResultState.WIN)
                dealer.updateGameResultState(GameResultState.LOSE)
                return@forEach
            }
            player.updateGameResultState(GameResultState.DRAW)
            dealer.updateGameResultState(GameResultState.DRAW)
        }
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}
