package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Money

class Dealer(override val cardHold: CardHold = CardHold()) : GameMember {
    override val name: String = "딜러"
    override var money: Money = Money()
    val playersMoney: MutableMap<GamePlayer, Money> = mutableMapOf()

    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    fun compareScore(other: GamePlayer) {
        if (getPoints() > 21) {
            giveMoneyToPlayer(other)
            return
        }

        if (other.getPoints() > 21) {
            collectMoneyFromPlayer(other)
            return
        }

        if (getPoints() == 21 && getCardHoldSize() == 2 && other.getPoints() == 21 && other.getCardHoldSize() == 2) {
            draw(other)
            return
        }

        if (other.getPoints() == 21 && other.getCardHoldSize() == 2) {
            giveMoneyToPlayerInSpecial(other)
            return
        }

        if (this.getPoints() > other.getPoints()) {
            collectMoneyFromPlayer(other)
            return
        }

        if (this.getPoints() < other.getPoints()) {
            giveMoneyToPlayer(other)
            return
        }

        if (this.getCardHoldSize() < other.getCardHoldSize()) {
            giveMoneyToPlayer(other)
            return
        }

        draw(other)
    }

    fun getMoneyFromPlayer(player: GamePlayer, amount: Int) {
        val money = player.betMoney(amount)
        playersMoney[player] = money
    }

    fun giveMoneyToPlayer(player: GamePlayer) {
        val money = playersMoney[player] ?: Money()
        val dealerMoney = betMoney(money.value)
        player.winMoney(money + dealerMoney)
    }

    fun giveMoneyToPlayerInSpecial(player: GamePlayer) {
        val money = playersMoney[player] ?: Money()
        val dealerMoney = betMoney(money.value * 150 / 100)
        player.winMoney(money + dealerMoney)
    }

    fun collectMoneyFromPlayer(player: GamePlayer) {
        val money = playersMoney[player] ?: Money()
        winMoney(money)
    }

    fun draw(player: GamePlayer) {
        val money = playersMoney[player] ?: Money()
        player.winMoney(money)
    }
}
