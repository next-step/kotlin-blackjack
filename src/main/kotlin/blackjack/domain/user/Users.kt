package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.InputInterface
import blackjack.domain.OutputInterface
import blackjack.domain.card.Deck

/**
 * 유저들을 저장하는 일급 컬렉션
 * Created by Jaesungchi on 2022.06.07..
 */
class Users(val users: List<User>, private val deck: Deck, val dealer: Dealer) {
    // TODO 3개 말고 2개로 줄이자...
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
        dealer.hitStage(deck, input, output)
    }

    companion object {
        const val INIT_CARD_SIZE = 2
        fun of(usersNames: List<String>, deck: Deck): Users {
            return Users(
                users = usersNames.map { User(it, deck.takeCards(INIT_CARD_SIZE)) },
                deck = deck,
                dealer = Dealer(deck.takeCard())
            )
        }
    }
}
