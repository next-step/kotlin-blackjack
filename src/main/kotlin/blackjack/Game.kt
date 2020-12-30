package blackjack

class Game(players: List<Player>, private val deck: Deck) {
    private val players = Players(players as MutableList<Player>)
    private val turn = 0

    constructor(PlayNames: String, deck: Deck = Deck()) : this(
        PlayNames.split((",")).filterNot { it.isBlank() }.map { Player(it.trim()) },
        deck
    )

    init {
        for (i in 0 until DEFAULT_CARD_SIZE) {
            this.players.getCardAllPlayer(deck.popDeck()!!)
        }
    }

    fun amountOfCards(): Int {
        return players.totalAmountOfCards()
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
