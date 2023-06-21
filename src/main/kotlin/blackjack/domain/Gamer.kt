package blackjack.domain

class Gamer(override val name: String, override val deck: Deck = Deck()) : Player {
    private val interDeck = deck.copy()
    private var score: Int = 0

    init {
        score = interDeck.score()
    }

    override fun score(): Int = score

    override fun addCard(card: Card) {
        check(isAddable()) {
            "현재 점수가 21점 이상이기에 카드를 추가할 수 없습니다."
        }

        interDeck.add(card)
        score += card.score(acc = score)
    }

    override fun addCardAll(values: Collection<Card>) = values.forEach(::addCard)

    override fun isAddable(): Boolean = score < 21

    override fun currentDeck(): Deck = interDeck.copy()
}
