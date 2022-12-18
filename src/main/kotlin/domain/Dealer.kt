package domain

class Dealer : GameParticipator() {

    override fun canDrawCard() = choiceBestScore() <= DRAW_CARD_SCORE_BEFORE

    fun pickWinner(player: Player): GameParticipator? {
        if (isLoser()) {
            return player
        }
        if (player.isLoser()) {
            return this
        }
        val scoreDifference = player.choiceBestScore() - this.choiceBestScore()
        if (scoreDifference == 0) {
            return null
        }
        if (scoreDifference > 0) {
            return player
        }
        return this
    }

    companion object {
        private const val DRAW_CARD_SCORE_BEFORE = 16
    }
}
