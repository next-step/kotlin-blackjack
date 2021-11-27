package blackjack.domain

class BlackJackManager(
    private val players: Players
) {

    fun giveInitialCards(cardDeck: CardDeck) {
        repeat(INITIAL_CARD_NUM) {
            players.eachAcceptCards(cardDeck)
        }
    }

    companion object {
        private const val INITIAL_CARD_NUM = 2
    }
}
