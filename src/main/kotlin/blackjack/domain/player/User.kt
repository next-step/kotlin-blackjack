package blackjack.domain.player

import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

/**
 * 유저데이터를 갖고 있는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class User(name: String, initCards: List<Card>) : Player(name, initCards) {
    var money = Money()
        private set

    fun setBatMoney(money: Int) {
        this.money += money
    }

    fun getBatResult(user: Player): Money {
        return match(user).earningMoney(money)
    }

    override fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface) {
        while (!isBust()) {
            output.drawMoreCard(this)
            if (!input.getYesOrNo()) return
            hit(deck.takeCard())
            output.drawUserCard(this)
        }
    }
}
