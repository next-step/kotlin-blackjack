package study.blackjack.model

/**
 * @author 이상준
 */
class BlackjackPlayer(
    private val name: String,
    private val cards: Cards = Cards(),
) {
    private var match: Match = Match.WAIT

    fun name(): String {
        return name
    }

    fun addCard(card: Card) {
        this.cards.addCard(card)
    }

    fun addAllCards(cards: Cards) {
        this.cards.addAllCards(cards)
    }

    fun cards(): Cards {
        return cards
    }

    fun result(): String {
        return this.match.text
    }

    fun match(dealer: BlackjackPlayer) {
        this.match = Match.of(this.cards().calculateScore(), dealer.cards().calculateScore())
    }
}
