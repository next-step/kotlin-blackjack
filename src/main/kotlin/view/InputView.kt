package view

object InputView {
    fun inputPlayerName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputData = readln()
        validatePlayerNameInputData(inputData)
        return inputData
    }

    fun isHit(playerName: String): String {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }

    private fun validatePlayerNameInputData(inputData: String) {
        validateZeroPlayerCnt(inputData)
    }

    private fun validateZeroPlayerCnt(inputData: String) {
        val nameList = inputData.split(',')
            .map { it.trim() }
            .filter { it.isNotBlank() }

        require(nameList.isNotEmpty()) { "출전할 플레이어는 0명 입니다." }
    }
}
