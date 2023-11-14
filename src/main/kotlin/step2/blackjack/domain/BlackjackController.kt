package step2.blackjack.domain

import step2.blackjack.domain.DealCard.choiceDealCards
import step2.blackjack.domain.DealCard.dealCard
import step2.blackjack.model.User
import step2.blackjack.model.UserList
import step2.blackjack.view.InputView
import step2.blackjack.view.ResultView

/**
 * 블랙잭 컨트롤러
 * */
private const val FIRST_DEAL_COUNT = 2

object BlackjackController {
    fun runBlackJack() {
        val userListText = InputView.drawInputUserListView()
        val userList = UserList.from(userListText)
        ResultView.drawDealCardDescription(userList)

        userList.dealCard(FIRST_DEAL_COUNT)
        ResultView.drawUserListStatus(userList)

        val choiceDealListener: (User) -> String = { user ->
            InputView.drawInputDealCard(user)
        }

        userList.choiceDealCards(choiceDealListener) { user ->
            ResultView.drawUserStatus(user)
        }

        ResultView.drawUserListStatusResult(userList)
    }
}