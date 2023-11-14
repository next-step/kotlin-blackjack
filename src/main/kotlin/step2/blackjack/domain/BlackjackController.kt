package step2.blackjack.domain

import step2.blackjack.model.UserList
import step2.blackjack.view.InputView
import step2.blackjack.view.ResultView



object BlackjackController {
    fun runBlackJack() {
        val userListText = InputView.drawInputUserListView()
        ResultView.drawDealCardDescription()

        val userList = UserList.from(userListText)


        ResultView.drawDealCardDescription()
    }
}
