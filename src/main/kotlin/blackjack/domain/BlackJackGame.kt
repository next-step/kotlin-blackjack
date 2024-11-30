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

    fun drawCard(player: Player) {
        val newCard = deck.drawCard()
        player.addCard(newCard)
    }

    fun getPlayerResults(): Map<String, Pair<String, Int>> {
        return players.associate { player ->
            val total = player.calculateTotal()
            player.getName() to Pair(player.displayHand(), total)
        }
    }

    companion object {
        fun createGame(
            playerNames: List<PlayerName>,
            deck: Deck,
        ): BlackJackGame {
            val players =
                playerNames.map { name ->
                    val handCards = listOf(deck.drawCard(), deck.drawCard())
                    Player(name, handCards)
                }
            return BlackJackGame(players, deck)
        }
    }
}
