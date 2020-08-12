package blackjack

class Player(val name: String) {
    private val cards = Cards()
    fun getCards() = cards

    fun selectValue(selectValue: String) {
        if (selectValue == "y") {
            cards.addCard()
        }
    }
}
