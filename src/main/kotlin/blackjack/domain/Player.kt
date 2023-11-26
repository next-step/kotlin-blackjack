package blackjack.domain

class Player(val name: String) {
    var cards: Cards = Cards(mutableListOf())
    var status = Status.HIT

    fun getScore(): Int {
        return Score(cards.cards.map { it.denomination }).calculate()
    }

    fun receiveCard(card: Card) {
        cards = cards.addCard(card)
        if (getScore() > Score.TARGET_SCORE) {
            setStand()
        }
    }

    fun setStand() {
        status = Status.STAND
    }

}
