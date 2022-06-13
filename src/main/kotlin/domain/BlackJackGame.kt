package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>) {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
    }

    fun isBust(player: Player, isIssued: Boolean): Boolean {
        var isExceed21 = false
        if (isIssued) {
            provideCard(player)
            if (player.getSumOfCards() >= BUST_THRESHOLD_SCORE) isExceed21 = true
        }

        return isExceed21
    }

    fun endCheck(noCnt: Int, playersSize: Int, isExceed21: Boolean): Boolean {
        return (noCnt != playersSize || isExceed21).not()
    }

    private fun provideCard(player: Player) {
        player.offer(ordinaryIssue())
    }

    private fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(INITIAL_POP_SIZE)
    }

    private fun ordinaryIssue(): MutableList<Card> {
        return CardDeck.pop(ORDINARY_POP_SIZE)
    }

    private const val BUST_THRESHOLD_SCORE = 21
    private const val INITIAL_POP_SIZE = 2
    private const val ORDINARY_POP_SIZE = 1
}
