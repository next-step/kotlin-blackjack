package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>) {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
    }

    fun checkBustCondition(player: Player, isIssued: Boolean): Boolean {
        var isExceed21 = false
        if (isIssued) {
            player.offer(CardDeck.pop(1))
            if (player.getSumOfCards() >= BUST_THRESHOLD_SCORE) isExceed21 = true
        }

        return isExceed21
    }

    fun endCheck(noCnt: Int, playersSize: Int, isExceed21: Boolean): Boolean {
        return (noCnt != playersSize || isExceed21).not()
    }

    private fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(2)
    }

    private const val BUST_THRESHOLD_SCORE = 21
}
