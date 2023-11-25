package blackjack.domain

import blackjack.domain.state.Hit

class Dealer(
    private val deck: Deck,
    val hit: Hit = Hit(Hand()),
) : User(hit) {

    override fun hit(card: Card) {
        require(status.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }

        status = status.draw(card)
        if (status.getSum() in 17..21) {
            status = status.stay()
        }
    }

    override fun init(cards: List<Card>) {
        super.init(cards)
        if (status.getSum() in 17..20) {
            status = status.stay()
        }
    }

    fun drawInitCards(): List<Card> {
        return deck.init()
    }

    fun draw(): Card {
        return deck.hit()
    }
}
