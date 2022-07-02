package blackjack.domain.player

import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card

/**
 * 유저데이터를 갖고 있는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class User(name: String, initCards: List<Card>) : Player(name, initCards) {
    constructor(name: String, vararg initCards: Card) : this(name, initCards.asList())

    var income = Money()
        private set

    var money = Money()
        private set

    fun setBetMoney(money: Int) {
        this.money += money
    }

    fun matchWithDealer(dealer: Dealer) {
        addIncome(match(dealer).earningMoney(money))
    }

    private fun addIncome(money: Money) {
        income += money
    }

    override fun isReadyToHit(input: InputInterface, output: OutputInterface): Boolean {
        if (isBust()) return false
        output.drawMoreCard(this)
        return input.getYesOrNo()
    }

    override fun afterHit(output: OutputInterface) {
        output.drawUserCard(this)
    }
}
