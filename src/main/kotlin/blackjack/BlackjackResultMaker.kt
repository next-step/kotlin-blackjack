package blackjack

import blackjack.view.PlayerResultDto

object BlackjackResultMaker {

    fun result(dealer: Dealer, players: List<Player>): List<PlayerResultDto> {
        var dealerWinningCount = 0
        var dealerLosingCount = 0
        var dealerTieCount = 0
        val playerResultDtos = players.map {
            val result = it.flip(dealer)
            when (result) {
                GameResult.LOSE -> dealerWinningCount++
                GameResult.WIN -> dealerLosingCount++
                GameResult.TIE -> dealerTieCount++
            }
            PlayerResultDto.playerDtoOf(it, result)
        }
        return listOf(PlayerResultDto(dealer, dealerWinningCount, dealerTieCount, dealerLosingCount)) + playerResultDtos
    }
}

enum class GameResult {
    WIN, LOSE, TIE
}
