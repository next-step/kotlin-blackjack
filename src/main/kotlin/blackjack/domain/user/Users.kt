package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.card.Deck
import blackjack.view.OutputInterface

/**
 * 유저들을 저장하는 일급 컬렉션
 * Created by Jaesungchi on 2022.06.07..
 */
class Users(val users: List<User>, private val deck: Deck, val dealer: Dealer) {

    init {
        require(users.isNotEmpty()) { ErrorMessages.USER_IS_EMPTY }
    }

    fun names(): List<String> {
        return users.map { it.name }
    }

    fun hit(output: OutputInterface) {
        users.forEach {
            it.hitStage(deck, output)
        }
        dealer.hitStage(deck, output)
    }

    private fun hitStageDealer(output: OutputInterface) {
        while (!dealer.isOverHitScore()) {
            output.printDealerHitMessage()
            dealer.hit(deck.takeCard())
        }
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
        fun of(usersNames: List<String>, deck: Deck): Users {
            return Users(
                users = usersNames.map { User(it, deck.takeCards(INIT_CARD_SIZE)) },
                deck = deck,
                dealer = Dealer(deck.takeCard())
            )
        }
    }
}
