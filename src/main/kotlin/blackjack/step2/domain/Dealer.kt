package blackjack.step2.domain

class Dealer(
    override val name: String = DEALER_DEFAULT_NAME,
    override val cards: Cards = Cards.of(emptyList()),
) : Participant(name, cards) {
    override fun pickCard(card: Card): Dealer {
        return Dealer(name, cards.add(card))
    }

    override fun canDraw(): Boolean {
        return this.score() < DEALER_DRAW_THRESHOLD // 딜러는 17 미만일 때만 카드를 받을 수 있음
    }

    override fun playTurn(
        cardPicker: CardPicker,
        interactor: GameInteractor,
    ): Dealer {
        return generateSequence(this) { currentDealer ->
            if (!currentDealer.canDraw()) {
                null
            } else {
                interactor.notifyDealerDraw()
                val card = cardPicker.pick()
                currentDealer.pickCard(card)
            }
        }.last()
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_THRESHOLD = 17
    }
}
