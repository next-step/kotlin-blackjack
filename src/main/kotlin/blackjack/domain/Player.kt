package blackjack.domain

class Player(val name: String, private val dealer: Dealer) {
    private val cardList: MutableList<Card> = dealer.drawMany(BlackjackRule.initialCard).toMutableList()

    fun getCardList(): List<Card> {
        return cardList.toList()
    }
}
