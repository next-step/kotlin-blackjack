package blackjack.model

class Players(playerNames: List<String>) {
    val playerList: List<Player>

    init {
        playerList = playerNames.map { Player(it, CardDeck(CardDealer.getCards(CardDeck.FIRST_CARD_COUNT))) }
    }

}
