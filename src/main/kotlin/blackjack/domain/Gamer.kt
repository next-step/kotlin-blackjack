package blackjack.domain

class Gamer(override val name: String, override val deck: Deck = Deck()) : Player {
    private val interDeck = deck.copy()

    override fun calculateScore(): Int = interDeck.score()

    override fun addCard(card: Card) {
        check(isAddable()) {
            "현재 점수가 21점 이상이기에 카드를 추가할 수 없습니다."
        }

        interDeck.add(card)
    }

    override fun addCardAll(values: Collection<Card>) = values.forEach(::addCard)

    override fun isAddable(): Boolean = interDeck.score() < Game.THRESHOLD

    override fun currentDeck(): Deck = interDeck.copy()
}
