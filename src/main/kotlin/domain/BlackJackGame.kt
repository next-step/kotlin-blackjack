package domain

object BlackJackGame {

    fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(2)
    }

    fun setInitialCards(players: List<Player>): List<Player> {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }

        return players
    }

    fun issue(): MutableList<Card> {
        return CardDeck.pop(1)
    }
}
