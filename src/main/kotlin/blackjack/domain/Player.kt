package blackjack.domain

class Player(
    val name: String,
    val hand: Hand,
    var status: PlayerStatus = PlayerStatus.INIT,
) {
    init {
        if (canBeBlackJack()) {
            status = PlayerStatus.BLACKJACK
        }
    }

    fun stay() {
        status = PlayerStatus.STAY
    }

    fun hit(card: Card) {
        hold(card)
        status =
            if (canBeBlackJack()) PlayerStatus.BLACKJACK
            else if (isBurst()) PlayerStatus.BURST
            else PlayerStatus.HIT
    }

    private fun hold(card: Card) {
        hand.add(card)
    }

    fun bestHandTotal(): Int {
        val handTotals = mutableListOf(hand.total())
        if (hand.hasAce) {
            handTotals.add(hand.total() + ACE_SOFT)
        }
        return handTotals
            .filter { it <= BLACKJACK }
            .maxOrNull() ?: -1
    }

    private fun canBeBlackJack(): Boolean {
        return bestHandTotal() == BLACKJACK
    }

    private fun isBurst(): Boolean {
        return hand.total() > BLACKJACK
    }

    companion object {
        const val MAX_NAME_SIZE = 10
        fun of(name: String, pairOfCard: Pair<Card, Card>): Player {
            require(name.length <= MAX_NAME_SIZE) { println("이름은 최대 ${MAX_NAME_SIZE} 글자 입니다.") }
            return Player(name, Hand.create(pairOfCard))
        }
    }
}

enum class PlayerStatus {
    INIT, // 쳐음 카드를 받은 상태
    HIT, // 추가 카드를 요청한 상태. 아직 플레이어의 차례임
    STAY, // 카드를 더 이상 받지 않는 상태
    BLACKJACK, // 총합이 21점인 상태
    BURST // 총합이 21점 초과인 상태
}
