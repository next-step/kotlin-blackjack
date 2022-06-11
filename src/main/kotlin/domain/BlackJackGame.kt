package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>): List<Player> {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
        return players
    }

    fun issue(player: Player, isIssued: Boolean) {
        if (isIssued)
            player.offer(CardDeck.pop(1))
    }

    private fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(2)
    }
}
