package blackjack

class Player(val name: String) {
    val cards = mutableListOf<Card>()

    fun receiveCard(card: Card) {
        cards.add(card)
    }

}
