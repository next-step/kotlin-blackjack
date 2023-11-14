package step2.blackjack.view

/**
 * 입력 뷰
 * */

private const val INPUT_USER_LIST_DESCRIPTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

object InputView {
    fun drawInputUserListView(): String {
        println(INPUT_USER_LIST_DESCRIPTION)
        return readln()
    }
}
