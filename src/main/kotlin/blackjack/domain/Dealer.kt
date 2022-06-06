package blackjack.domain

class Dealer(private val deck: Deck) : Player("딜러") {

    fun giveCard(player: Player) {
        player.addCard(deck.draw())
    }

    fun startGame(names: List<String>): List<Player> {
        repeat(INIT_CARD_COUNT) { giveCard(this) }

        return names.map { playerName ->
            Player(playerName).also { player -> repeat(INIT_CARD_COUNT) { giveCard(player) } }
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
