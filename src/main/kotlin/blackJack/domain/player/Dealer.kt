package blackJack.domain.player

import blackJack.domain.enums.Status.HIT

class Dealer(val name: String) : Participant() {

    override fun isContinued(): Boolean {
        val totalScore = cards.calculateTotalScore()
        return status == HIT && totalScore < 17
    }

    companion object {
        private const val DEALER_NAME = "딜러"

        fun createDealer(): Dealer {
            return Dealer(DEALER_NAME)
        }
    }
}
