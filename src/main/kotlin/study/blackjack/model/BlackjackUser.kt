package study.blackjack.model

/**
 * @author 이상준
 */
class BlackjackUser(
    name: String,
    private var money: Money = Money(0),
    private var cards: Cards = Cards(listOf()),
) {
    var name = name
        private set
    private var match: Match = Match.WAIT

    fun money(amount: Int) {
        this.money = Money(amount)
    }

    fun addCard(card: Card) {
        cards = cards.add(card)
    }

    fun cards(): Cards {
        return cards
    }

    fun profit(): Double {
        return match.operation(money.amount)
    }

    fun match(dealer: BlackjackUser): Match {
        match = Match.of(this.cards.calculateScore(), dealer.cards.calculateScore())
        return match
    }

    fun isReceiveCard(): Boolean {
        return cards.calculateScore() <= DEALER_SCORE
    }

    companion object {
        private const val DEALER_SCORE = 16
    }
}
