package step2.blackjack.view

import step2.blackjack.model.User

/**
 * 입력 뷰
 * */

private const val INPUT_USER_LIST_DESCRIPTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
private const val INPUT_DEAL_CARD_DESCRIPTION = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

object InputView {
    fun drawInputUserListView(): String {
        println(INPUT_USER_LIST_DESCRIPTION)
        val userListText = readln()
        println()
        return userListText
    }

    fun drawInputDealCard(user: User): String {
        println(INPUT_DEAL_CARD_DESCRIPTION.format(user.name))
        return readln()
    }
}
