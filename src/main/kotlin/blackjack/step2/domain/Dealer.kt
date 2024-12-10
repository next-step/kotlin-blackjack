package blackjack.step2.domain

class Dealer(
    override val name: String = "딜러",
    override val cards: Cards = Cards.of(emptyList()),
) : Participant(name, cards) {
    override fun pickCard(card: Card): Dealer {
        return Dealer(name, cards.add(card))
    }

    override fun canDraw(): Boolean {
        return this.score() < 17 // 딜러는 17 미만일 때만 카드를 받을 수 있음
    }
}
