package blackjack.domain.participant

import blackjack.domain.card.Cards

class Player(
    name: String,
    cards: Cards = Cards(),
    val bettingAmount: Int,
) : Participant(name, cards) {

    init {
        require(bettingAmount > 0) {
            "배팅 금액은 0원보다 커야 합니다"
        }
    }

    override fun canReceiveCard(): Boolean = !cards.isBlackjack() && !cards.isBusted()

}
