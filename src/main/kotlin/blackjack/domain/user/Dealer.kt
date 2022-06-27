package blackjack.domain.user

import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCard: Card) : User(DEALER_NAME, listOf(initCard)) {
    fun getBatResult(users: List<User>): Money {
        var money = Money(0)
        users.forEach {
            when (match(it)) {
                Match.WIN -> {
                    money += it.money
                }
                Match.LOSE -> {
                    money -= if (it.isBlackJack())
                        it.money.times(BLACKJACK_WIN_PROFIT_MARGIN)
                    else
                        it.money
                }
            }
        }
        return money
    }

    fun isOverHitScore(): Boolean {
        return cards.getScore().value >= HIT_SCORE
    }

    override fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface) {
        while (!isOverHitScore()) {
            output.drawDealerHitMessage()
            hit(deck.takeCard())
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_SCORE = 17
    }
}
