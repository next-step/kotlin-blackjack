package blackjack.view

fun inputPlayerName(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

    val inputPlayerNames: String? = readlnOrNull()
    requireNotNull(inputPlayerNames) { "플레이어 이름들이 입력되지 않았습니다" }

    val playerNames = inputPlayerNames.split(",")
    require(playerNames.size == 2) { "플레이어는 최소 2명 이상 입력되어야합니다" }

    return playerNames
}
