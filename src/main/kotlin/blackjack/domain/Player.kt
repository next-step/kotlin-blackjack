package blackjack.domain

class Player(val name: String) {
    var cards = Cards(mutableListOf())
    val isBusted get() = getScore() > 21

    fun getScore(): Int {
        return Score(cards.cards.map { it.denomination }).calculate()
    }

    fun getFirstTwoCards(twoCards: List<Card>){
        twoCards.forEach { cards.addCard(it) }
    }
    fun hit(card: Card) {
        cards.addCard(card)
    }
}
