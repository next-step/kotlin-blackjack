package blackJack.domain.player

class Dealer(
    val name: String = DEALER_NAME,
    val status: PlayingAreaImpl = PlayingAreaImpl.of()
) : PlayingArea by status {

    override fun getAbleReceivedCard(): Boolean =
        getScore() < ABLE_MAXIMUM_SUM

    companion object {
        private const val DEALER_NAME = "DEALER"
        private const val ABLE_MAXIMUM_SUM = 17
    }
}
