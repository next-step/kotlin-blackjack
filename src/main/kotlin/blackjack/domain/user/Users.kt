package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.card.Deck
import blackjack.view.InputView
import blackjack.view.OutputInterface

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

    fun hit(output: OutputInterface) {
        users.forEach {
            hitStage(it, output)
        }
    }

    private fun hitStage(user: User, output: OutputInterface) {
        while (!user.cards.isBust()) {
            output.printMoreCard(user)
            if (!InputView.getYesOrNo()) return
            user.cards.addCard(deck.takeCard())
            output.printUserCard(user)
        }
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
        fun of(usersNames: List<String>, deck: Deck): Users {
            return Users(usersNames.map { User(it, deck.takeCards(INIT_CARD_SIZE)) }, deck)
        }
    }
}
