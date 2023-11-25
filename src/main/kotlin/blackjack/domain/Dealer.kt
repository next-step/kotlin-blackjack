package blackjack.domain

import blackjack.domain.state.Hit

class Dealer(
    private val deck: Deck,
    val hit: Hit = Hit(Hand()),
) : User(hit) {

    override fun hit(card: Card) {

        require(status.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }
        status.receive(card)

        state = if (status.getSum() > 21) {
            State.BUST
        } else if (status.getSum() > 16) {
            State.STAND
        } else {
            State.HIT
        }
    }

    override fun init(cards: List<Card>) {
        super.init(cards)
        if (status.getSum() == BLACKJACK) {
            state = State.BLACKJACK
        } else if (status.getSum() > 16) {
            state = State.STAND
        }
    }

    fun drawInitCards(): List<Card> {
        return deck.init()
    }

    fun draw(): Card {
        return deck.hit()
    }
}
