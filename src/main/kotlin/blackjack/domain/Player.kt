package blackjack.domain

class Player(val name: String) {
    private var myCards: Cards = Cards.empty()

    fun showMyCards() {
        println(myCards.toString())
    }

    fun drawCard() {
        val drawnCard = GameCardsSet.drawRandomCard()
        if (drawnCard != null) {
            myCards = myCards.add(drawnCard)
        }
    }

    fun countCards(): Int = myCards.cards.size
}
