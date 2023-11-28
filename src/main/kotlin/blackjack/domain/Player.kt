package blackjack.domain

class Player(val name: String) {
    var cards = Cards(mutableListOf())

    fun getScore(): Int {
        return Score(cards.cards.map { it.denomination }).calculate()
    }

    fun hit(card: Card) {
        cards = cards.addCard(card)
//        if (getScore() > Score.TARGET_SCORE) {
//            status = Status.STAND
//        }
    }
}
