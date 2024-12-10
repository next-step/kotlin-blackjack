package study.blackjack.model

/**
 * @author 이상준
 */
class BlackjackPlayer(
    private val name: String,
    private var cards: Cards = Cards(listOf()),
) {
    private var match: Match = Match.WAIT

    fun name(): String {
        return name
    }

    fun addCard(card: Card) {
        cards = cards.add(card)
    }

    fun mergeCards(cards: Cards) {
        this.cards = cards.merge(cards)
    }

    fun cards(): Cards {
        return cards
    }

    fun result(): String {
        return this.match.text
    }

    fun match(dealer: BlackjackPlayer) {
        this.match = Match.of(this.cards.calculateScore(), dealer.cards.calculateScore())
    }
}
