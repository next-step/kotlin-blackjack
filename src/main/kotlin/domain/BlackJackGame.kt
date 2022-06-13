package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>) {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
    }

    fun isBust(player: Player, isIssued: Boolean): Boolean {
        var isBust = false
        if (isIssued) {
            if (player.getSumOfCards() >= BUST_THRESHOLD_SCORE) isBust = true
        }

        return isBust
    }

    fun endCheck(noCnt: Int, playersSize: Int, isBust: Boolean): Boolean {
        return (noCnt != playersSize || isBust).not()
    }

    fun drawCard(player: Player) {
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
