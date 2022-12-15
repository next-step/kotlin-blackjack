package domain

class Dealer : GameParticipator() {

    override fun canDrawCard(): Boolean = choiceBestScore() <= DRAW_CARD_SCORE_BEFORE

    companion object {
        private const val DRAW_CARD_SCORE_BEFORE = 16
    }

}
