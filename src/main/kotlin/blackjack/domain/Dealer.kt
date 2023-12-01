package blackjack.domain

class Dealer() : BlackjackParticipant(DEALER_NAME){
    override val canHit: Boolean
        get() = getScore() < DEALER_TARGET_SCORE


    companion object{
        private const val DEALER_NAME = "딜러"
        private const val DEALER_TARGET_SCORE = 17
    }
}
