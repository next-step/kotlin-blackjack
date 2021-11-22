package blackjack.domain

class BlackJackManager(
    private val players: Players
) {

    fun giveInitialCards(cardDeck: CardDeck) {
        repeat(2) {
            players.eachAcceptCards(cardDeck)
        }
    }
}
