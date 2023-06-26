package blackjack.domain

class BlackjackGame(input: List<String>) {

    private val players = mutableListOf<Player>()
    private val deck = Deck.make()

    init {
        transformToPlayers(input)
    }

    private fun transformToPlayers(input: List<String>) {
        val playerList = input.map {
            Player(it.trim()).apply {
                cards.add(deck.draw())
                cards.add(deck.draw())
            }
        }
        players.addAll(playerList)
    }

    fun deal(
        answer: Answer,
        player: Player
    ) {
        when (answer) {
            Answer.HIT -> {
                player.hit(deck.draw())
            }
            Answer.STAY -> {
            }
        }
    }

    fun getPlayers() = players

    fun getDeck() = deck
}
