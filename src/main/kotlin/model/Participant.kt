package model

open class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()
    val scoreState get() = ScoreStateGenerator().generate(cards)
    var bettingMoney: Int = 0
        private set

    fun receiveCard(card: Card): Boolean {
        return _cards.add(card)
    }

    fun updateBettingMoney(money: Int) {
        bettingMoney = money
    }
}
