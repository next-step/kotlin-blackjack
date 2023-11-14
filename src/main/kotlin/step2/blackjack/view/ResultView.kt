package step2.blackjack.view

import step2.blackjack.domain.CardCount.getAroundCount
import step2.blackjack.model.User
import step2.blackjack.model.UserList

/**
 * 출력 뷰
 * */

private const val USER_STATUS_FORMAT = "%s카드: %s"
private const val USER_STATUS_RESULT_FORMAT = "%s카드: %s - 결과: %d"
private const val DEAL_CARD_DESCRIPTION = "%s에게 %d장의 나누었습니다."
private const val DEAL_CARD_COUNT = 2
private const val DEFAULT_AROUND_COUNT = 21

object ResultView {
    fun drawDealCardDescription(userList: UserList, dealCardCount: Int = DEAL_CARD_COUNT) {
        val userNameListText = userList.userList.joinToString { it.name.name }
        println(DEAL_CARD_DESCRIPTION.format(userNameListText, dealCardCount))
    }

    /**
     * 유저 리스트 상태 출력
     * */
    fun drawUserListStatus(userList: UserList) {
        userList.userList.forEach { user ->
            drawUserStatus(user)
        }
        println()
    }

    /**
     * 유저 상태 출력
     * */
    fun drawUserStatus(user: User) {
        println(USER_STATUS_FORMAT.format(user.name, user.cardList))
    }

    /**
     * 유저 리스트 결과 상태 출력
     * */
    fun drawUserListStatusResult(userList: UserList) {
        println()
        userList.userList.forEach { user ->
            drawUserStatusResult(user)
        }
    }

    /**
     * 유저 결과 상태 출력
     * */
    private fun drawUserStatusResult(user: User) {
        println(USER_STATUS_RESULT_FORMAT.format(user.name, user.cardList, user.cardList.getAroundCount(DEFAULT_AROUND_COUNT)))
    }
}
