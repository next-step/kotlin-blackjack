package blackJack.domain

class Dealer(
    name: String = DEALER_NAME,
) : GamePlayer(name = name) {

    override fun getAbleReceivedCard(): Boolean =
        getScore() < ABLE_MAXIMUM_SUM

    override fun isPlayer(): Boolean {
        return !super.isPlayer()
    }

    companion object {
        private const val DEALER_NAME = "DEALER"
        private const val ABLE_MAXIMUM_SUM = 17
    }
}
