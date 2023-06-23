package blackjack.domain

class Gamer(name: String, deck: Deck = Deck()) : Player(
    name = name,
    deck = deck.copy()
) {
    override fun addCard(card: Card) {
        check(isAddable()) {
            "현재 점수가 21점 이상이기에 카드를 추가할 수 없습니다."
        }.run { super.addCard(card) }
    }

    override fun isAddable(): Boolean = deck.score() < Game.THRESHOLD
}
