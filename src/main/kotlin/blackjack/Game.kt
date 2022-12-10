package blackjack

object Game {
    private const val START_CARD_SIZE = 2
    private val deck = Deck()
    val players: MutableList<Player> = mutableListOf()

    fun addPlayer(player: Player) {
        var newPlayer = player

        repeat(START_CARD_SIZE) {
            val getCardPlayer = newPlayer.getCards(deck.cards.pick())
            newPlayer = getCardPlayer
        }
        players.add(newPlayer)
    }
}
