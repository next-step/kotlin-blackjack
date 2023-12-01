package blackjack_dealer.dto

data class ParticipantBetAmount(
    private val betAmount: Int,
) {
    init {
        require(betAmount > -1) { INVALID_INPUT }
    }

    fun getBetAmount(): Int = betAmount

    companion object {
        private const val INVALID_INPUT = "올바른 값이 입력되지 않았습니다."
    }
}
