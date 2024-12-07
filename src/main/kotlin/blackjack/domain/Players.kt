package blackjack.domain

class Players(private val players: List<Player>) {
    fun distributeInitialCards(deck: Deck) {
        players.forEach { player ->
            repeat(2) {
                player.addCard(deck.draw())
            }
        }
    }

    fun hit(
        playerName: String,
        deck: Deck,
    ) {
        val player = findPlayerByName(playerName)
        player.addCard(deck.draw())
    }

    fun getPlayers(): List<Player> {
        return players.toList()
    }

    fun toResultDTO(): List<PlayerResultDTO> {
        return players.map { player ->
            PlayerResultDTO(
                name = player.name,
                cards = player.getCards().joinToString { "${it.rank}${it.suit}" },
                totalValue = player.getTotalValue(),
            )
        }
    }

    private fun findPlayerByName(name: String): Player {
        return players.find { it.name == name }
            ?: throw NoSuchElementException("Player with name $name not found")
    }
}
