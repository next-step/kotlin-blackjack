package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>): List<Player> {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
        return players
    }

    fun issueAndCheck(player: Player, isIssued: Boolean): Boolean {
        var isExceed21 = false
        if (isIssued) {
            player.offer(CardDeck.pop(1))
            if(player.getSumOfCards() >= 21) isExceed21 = true
        }

        return isExceed21
    }

    fun endCheck(noCnt: Int, playersSize: Int, isExceed21: Boolean): Boolean {
        return (noCnt != playersSize || isExceed21).not()
    }

    private fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(2)
    }
}
