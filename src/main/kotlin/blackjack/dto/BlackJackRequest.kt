package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.view.InputPlayerBetting

class BlackJackRequest(val players: List<Player>, val dealer: Dealer) {
    companion object {

        private const val CANNOT_CONVERT_INT = "정수로 변환할 수 업습니다."
        private const val NEGATIVE_ERROR = "음수는 입력할 수 없습니다."

        fun of(playersBetting: List<InputPlayerBetting>): BlackJackRequest {
            return BlackJackRequest(
                playersBetting.map {
                    Player(it.player, convertToBettingAmount(it.betting))
                },
                Dealer()
            )
        }

        private fun convertToBettingAmount(inputBettingAmount: String): Int {
            val convertToBettingAmount = inputBettingAmount.toIntOrNull() ?: throw IllegalArgumentException(CANNOT_CONVERT_INT)
            checkNegative(convertToBettingAmount)
            return convertToBettingAmount
        }

        private fun checkNegative(number: Int) {
            if (number < 0) throw IllegalArgumentException(NEGATIVE_ERROR)
        }
    }
}
