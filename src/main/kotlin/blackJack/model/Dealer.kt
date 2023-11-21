package blackJack.model

class Dealer(val name: String) {
    private var cardDeck = CardDeck.of()

    fun startGame(players: List<Player>): List<Player> {
        return initializePlayerHands(players)
    }

    private fun initializePlayerHands(players: List<Player>): List<Player> {
        players.forEach { player ->
            player.addCard(drawCard())
            player.addCard(drawCard())
        }

        return players
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

    fun isDrawCardAllowedFor(player: Player): Boolean {
        return player.calculateScore() < 21
    }

    fun countCard(): Int {
        return cardDeck.cards.size
    }
}
