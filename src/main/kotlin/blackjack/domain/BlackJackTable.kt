package blackjack.domain

object BlackJackTable {

    const val START_CARD_SIZE = 2
    const val DEFAULT_CARD_SIZE = 1

    private val blackJackCards: MutableList<Card> = generateBlackJackCards()

    lateinit var gamePlayers: List<Player>
    init {
        check(blackJackCards.size == blackJackCards.distinct().size) {
            "카드는 중복이면 안됨"
        }
    }

    private fun hitCard(): Card {
        val card = blackJackCards.random()
        blackJackCards.remove(card)
        return card
    }

    private fun generateBlackJackCards(): MutableList<Card> {
        return CardNumber.values().flatMap {
            getCard(it)
        }.toMutableList()
    }

    private fun getCard(cardNumber: CardNumber) = CardType.values().map { cardType ->
        Card(cardNumber, cardType)
    }

    fun setPlayers(players: List<Player>) {
        gamePlayers = players
    }

    fun giveCardsToPlayer(player: Player, repeatTime: Int = DEFAULT_CARD_SIZE) {
        repeat(repeatTime) {
            player.addCard(hitCard())
        }
    }
}
