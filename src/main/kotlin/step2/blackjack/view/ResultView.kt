package step2.blackjack.view

import step2.blackjack.model.User
import step2.blackjack.model.UserList

/**
 * 출력 뷰
 * */

private const val USER_STATUS_FORMAT = "%s카드: %s"
private const val DEAL_CARD_DESCRIPTION = "pobi, jason에게 %d장의 나누었습니다."
private const val DEAL_CARD_COUNT = 2

object ResultView {
    fun drawDealCardDescription(dealCardCount: Int = DEAL_CARD_COUNT) {
        println(DEAL_CARD_DESCRIPTION.format(dealCardCount))
    }

    /**
     * 유저 리스트의 상태 출력
     * */
    fun drawUserListStatus(userList: UserList) {
        userList.userList.forEach { user ->
            drawUserStatus(user)
        }
    }

    private fun drawUserStatus(user: User) {
        println(USER_STATUS_FORMAT.format(user.name, user.cardList))
    }
}
