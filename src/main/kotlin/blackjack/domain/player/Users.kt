package blackjack.domain.player

import blackjack.constant.ErrorMessages
import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

/**
 * 유저들을 저장하는 일급 컬렉션
 * Created by Jaesungchi on 2022.06.07..
 */
class Users(val users: List<User>, private val deck: Deck) {
    init {
        require(users.isNotEmpty()) { ErrorMessages.USER_IS_EMPTY }
    }

    fun names(): List<String> {
        return users.map { it.name }
    }

    fun hit(input: InputInterface, output: OutputInterface) {
        users.forEach {
            it.hitStage(deck, input, output)
        }
    }

    fun setBatMoney(input: InputInterface) {
        users.forEach {
            val money = input.getBatMoney(it)
            it.setBetMoney(money)
        }
    }

    fun matchWithDealer(dealer: Dealer) {
        users.forEach {
            it.matchWithDealer(dealer)
        }
    }

    fun getSumOfUsersIncome(): Money {
        return Money(users.sumOf { it.income.value })
    }

    companion object {
        fun of(usersNames: List<String>, deck: Deck): Users {
            return Users(
                users = usersNames.map { User(it, deck.takeCards(Cards.INIT_CARD_SIZE)) },
                deck = deck
            )
        }
    }
}
