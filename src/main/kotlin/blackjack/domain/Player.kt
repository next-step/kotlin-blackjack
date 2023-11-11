package blackjack.domain

class Player(val name: Nickname, private val dealer: Dealer) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> = _cards

    fun receiveCard() {
        val card = dealer.dealCard()
        _cards.add(card)
    }
}
