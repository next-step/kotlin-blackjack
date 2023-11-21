package blackJack.model

class Dealer(val name: String) {
    private var cardDeck = CardDeck.of()
    val MAXIMUM_SCORE = 21

    fun countCard(): Int {
        return cardDeck.cards.size
    }

    fun drawCard(): Card {
        val currentCard = cardDeck.cards
            .shuffled()
            .first()

        cardDeck = cardDeck.cards
            .filter { it != currentCard }
            .let(::CardDeck)

        return currentCard
    }

    fun startGame(players: List<Player>): List<Player> {
        return initializePlayerHands(players)
    }

    private fun initializePlayerHands(players: List<Player>): List<Player> {
        players.forEach { player ->
            player requestCardToDealer drawCard()
            player requestCardToDealer drawCard()
        }

        return players
    }
}

infix fun Dealer.checkDrawCardIsAllowedFor(player: Player): Boolean {
    return player.calculateScore() < MAXIMUM_SCORE
}
