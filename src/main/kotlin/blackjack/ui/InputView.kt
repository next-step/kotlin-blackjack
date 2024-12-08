package blackjack.ui

class InputView(val inputProvider: () -> String? = { readln() }) {
    fun inputUserNames(): UserNames {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return inputProvider()?.split(",") ?: throw IllegalArgumentException("입력이 없습니다.")
    }

    fun inputMore(name: UserName): UserMore {
        println("$name 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return inputProvider()?.let {
            it == "y"
        } ?: throw IllegalArgumentException("입력이 없습니다.")
    }
}
