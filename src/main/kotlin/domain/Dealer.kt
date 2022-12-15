package domain

class Dealer : GameParticipator() {

    override fun canDrawCard() = choiceBestScore() <= DRAW_CARD_SCORE_BEFORE

    fun isLoser() = choiceBestScore() > Score.MAX_SCORE

    companion object {
        private const val DRAW_CARD_SCORE_BEFORE = 16
    }

}
