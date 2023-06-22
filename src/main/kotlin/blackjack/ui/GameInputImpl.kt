package blackjack.ui

object GameInputImpl : GameInput {
    override fun requestPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readlnOrNull() ?: return emptyList()

        return playerNames.split(",")
            .filter(String::isNotBlank)
            .map(String::trim)
    }

    override fun requestConfirmDrawCard(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }
}
