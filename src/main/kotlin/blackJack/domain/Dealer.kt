package blackJack.domain

class Dealer : GamePlayer(name = DEALER_NAME, type = GamePlayerType.DEALER) {

    override fun getAbleReceivedCard(): Boolean = getScore() < ABLE_MAXIMUM_SUM

    companion object {
        private const val DEALER_NAME = "DEALER"
        private const val ABLE_MAXIMUM_SUM = 17
    }
}
