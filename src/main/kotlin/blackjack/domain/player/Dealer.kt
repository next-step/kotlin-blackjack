package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Money

class Dealer(override val cardHold: CardHold = CardHold()) : GameMember {
    override val name: String = "딜러"
    override var money: Money = Money()

    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    fun compareScore(other: GamePlayer) {
        if (other.isExceedCardPoint()) {
            return
        }

        if (isExceedCardPoint()) {
            giveMoneyToPlayer(other)
            return
        }

        if (isBlackJack() && other.isBlackJack()) {
            draw(other)
            return
        }

        if (other.isBlackJack()) {
            giveMoneyToPlayerInSpecial(other)
            return
        }

        if (isBlackJack()) {
            return
        }

        if (this.getPoints() > other.getPoints()) {
            return
        }

        if (this.getPoints() < other.getPoints()) {
            giveMoneyToPlayer(other)
            return
        }

        draw(other)
    }

    fun getMoneyFromPlayer(player: GamePlayer, amount: Int) {
        val money = player.betMoney(amount)
        this.money += money
    }

    fun giveMoneyToPlayer(player: GamePlayer) {
        val money = player.getBettingMoney()
        val dealerMoney = betMoney(money.value * 2)
        player.winMoney(dealerMoney)
    }

    fun giveMoneyToPlayerInSpecial(player: GamePlayer) {
        val money = player.getBettingMoney()
        val dealerMoney = betMoney(money.value * 250 / 100)
        player.winMoney(dealerMoney)
    }

    fun draw(player: GamePlayer) {
        val money = player.getBettingMoney()
        player.winMoney(money)
    }
}
