package blackjack.domain

data class Participant(
    override val name: String
) : Player(name = name) {
    var bettingMoney: Int = 0
        private set

    override val canReceiveCard: Boolean
        get() {
            return score <= CAN_RECEIVE_CARD_SCORE
        }

    override fun receiveFirstTurnCard(blackjackShoe: BlackjackShoe) {
        repeat(NUMBER_OF_INIT_CARD) {
            receiveCard(blackjackShoe.draw())
        }
    }

    fun bet(bettingMoney: Int) {
        this.bettingMoney = bettingMoney
    }

    companion object {
        private const val CAN_RECEIVE_CARD_SCORE = 21
        private const val NUMBER_OF_INIT_CARD = 2
    }
}
