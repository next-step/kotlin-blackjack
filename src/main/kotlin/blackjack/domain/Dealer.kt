package blackjack.domain

class Dealer(
    private val deck: Deck,
    hand: Hand = Hand(),
) : User(hand) {
    override fun hit(card: Card) {
        require(hand.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }
        hand.receive(card)

        state = if (hand.getSum() > 21) {
            State.BUST
        } else if (hand.getSum() > 16) {
            State.STAND
        } else {
            State.HIT
        }
    }

    override fun init(cards: List<Card>) {
        super.init(cards)
        if (hand.getSum() == BLACKJACK) {
            state = State.BLACKJACK
        } else if (hand.getSum() > 16) {
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
