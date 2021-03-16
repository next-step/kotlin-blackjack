package blackjack.ui

class InputView {

    fun inputPlayer(): List<String> {
        println("# 게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return readLine()?.split(",") ?: throw IllegalArgumentException("유저의 이름은 빈칸일 수 없습니다.")
    }

    fun selectCardDraw(playerName: String): String {
        println("# ${playerName}는 한장의 카드를 더 받겠습니까? (Y/N)")
        return readLine() ?: throw IllegalArgumentException("Y 또는 N으로 대답하세요")
    }
}
