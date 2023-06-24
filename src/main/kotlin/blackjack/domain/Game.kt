package blackjack.domain

class Game {

    private val deck = Deck()
    fun firstDraw(playerList: List<Player>) {

        playerList.forEach {
            it.addCards(deck.drawTwoCard())
        }
    }
}
