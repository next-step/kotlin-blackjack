package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Dealer(
    private val deck: Deck,
    state: State = Hit(Hand()),
) : User(state) {

    override fun hit(card: Card) {
        require(state.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }

        super.hit(card)
        if (state.getSum() in 17..Blackjack.NUMBER) {
            state = state.stay()
        }
    }

    override fun init(cards: List<Card>) {
        super.init(cards)
        if (state.getSum() in 17..20) {
            state = state.stay()
        }
    }

    fun drawInitCards(): List<Card> {
        return deck.init()
    }

    fun draw(): Card {
        return deck.hit()
    }
}
