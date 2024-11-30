package blackjack.domain

class BlackJackGame(
    val players: List<Player>,
    private val deck: Deck,
) {
    fun getInitialPlayerCards(): Map<String, String> {
        return players.associate { player ->
            player.getName() to player.displayHand()
        }
    }

    fun askForMoreCards(player: Player): Pair<Boolean, Card?> {
        val newCard = deck.drawCard()
        player.addCard(newCard)
        return Pair(true, newCard)
    }

    fun shouldContinue(response: String): Boolean {
        return response.lowercase() == "y"
    }

    fun getPlayerResults(): Map<String, Pair<String, Int>> {
        return players.associate { player ->
            val total = player.calculateTotal()
            player.getName() to Pair(player.displayHand(), total)
        }
    }

    companion object {
        fun createGame(playerNames: String): BlackJackGame {
            val deck = Deck()
            val names = playerNames.split(",").map { it.trim() }
            val players =
                names.map { name ->
                    val handCards = listOf(deck.drawCard(), deck.drawCard())
                    Player(PlayerName(name), handCards)
                }
            return BlackJackGame(players, deck)
        }
    }
}
