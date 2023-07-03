package blackjack.view

private const val PLAYER_NAME_DELIMITER = ","

fun inputPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return readln().replace(" ", "")
        .split(PLAYER_NAME_DELIMITER)
}

fun inputPlayerBettingMoney(name: String): Int {
    println("${name}의 배팅 금액은?")
    return readln().toIntOrNull()?: retryInputPlayerBettingMoney(name)
}

fun requestAdditionalDraw(name: String): CommandView {
    println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    return CommandView.from(readln())?: retryInputPlayerDraw(name)
}

private fun retryInputPlayerBettingMoney(name: String): Int {
    println("숫자만 입력하여아합니다.")
    return inputPlayerBettingMoney(name)
}

private fun retryInputPlayerDraw(name: String): CommandView {
    println("지원하지 않는 커맨드이므로 다시 입력하세요.")
    return requestAdditionalDraw(name)
}
