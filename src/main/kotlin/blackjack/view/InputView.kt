package blackjack.view

object InputView {

    fun inputPlayers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼포 기준으로 분리)")
        return readlnOrNull() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }
}
