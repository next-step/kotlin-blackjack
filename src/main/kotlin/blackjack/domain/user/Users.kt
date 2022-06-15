package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.card.Deck
import blackjack.view.InputView
import blackjack.view.OutputView

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

    // TODO 여기 정리해야함!
    fun hit() {
        users.forEach {
            hitStage(it)
        }
    }

    private fun hitStage(user: User) {
        while (!user.cards.isBust()) {
            OutputView.printMoreCard(user)
            if (!InputView.getYesOrNo()) return
            user.cards.addCard(deck.takeCard())
            OutputView.printUserCard(user)
        }
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
        fun of(usersNames: List<String>, deck: Deck): Users {
            return Users(usersNames.map { User(it, deck.takeCards(INIT_CARD_SIZE)) }, deck)
        }
    }
}
