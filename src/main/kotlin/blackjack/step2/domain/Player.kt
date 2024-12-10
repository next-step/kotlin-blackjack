package blackjack.step2.domain

class Player(
    override val name: String,
    override val cards: Cards = Cards.of(emptyList()),
) : Participant(name, cards) {
    override fun pickCard(card: Card): Player {
        return Player(name, cards.add(card))
    }

    override fun canDraw(): Boolean {
        return !this.isBust() // 플레이어는 버스트 상태가 아니어야 추가 가능
    }
}
