package blackjack.domain

class Gamer(override val name: String, override val deck: Deck = Deck()) : Player {

    private var score: Int = 0

    init {
        score = deck.score()
    }

    override fun score(): Int = score

    override fun addCard(card: Card) {
        check(isAddable()) {
            "현재 점수가 21점 이상이기에 카드를 추가할 수 없습니다."
        }

        deck.add(card)
        score += card.score(acc = score)
    }

    override fun isAddable(): Boolean = score < 21
}
