package blackJack.domain.player

class Dealer(
    val name: String = DEALER_NAME,
    val status: State = StateImpl.of()
) : State by status {

    override fun getAbleReceivedCard(): Boolean =
        getScore() < ABLE_MAXIMUM_SUM

    companion object {
        const val DEALER_NAME = "딜러"
        private const val ABLE_MAXIMUM_SUM = 17
    }
}
