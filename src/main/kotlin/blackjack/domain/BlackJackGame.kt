package blackjack.domain

object BlackJackGame {

    private val deck = Deck.newDeck()

    fun startGame(playerNames: List<String>): Players {
        return Players(playerNames.map { Player(it, deck.getInitGameCards()) })
    }

    fun addMoreCard(): Card = deck.getCard()
}
