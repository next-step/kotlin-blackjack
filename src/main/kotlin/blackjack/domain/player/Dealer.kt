package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Money
import blackjack.domain.rule.Score

class Dealer(override val cardHold: CardHold = CardHold()) : GameMember {
    override val name: String = "딜러"
    override var money: Money = Money()
    val playersMoney: MutableMap<GamePlayer, Money> = mutableMapOf()

    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    fun compareScore(other: GamePlayer): Score {
        if (other.getPoints() > 21) return Score().win()
        if (getPoints() > 21) return Score().lose()
        return when {
            this.getPoints() > other.getPoints() -> Score().win()
            this.getPoints() < other.getPoints() -> Score().lose()
            this.getCardHoldSize() < other.getCardHoldSize() -> Score().win()
            else -> Score().draw()
        }
    }

    fun playersMoneyFromPlayer(player: GamePlayer, amount: Int) {
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
}
