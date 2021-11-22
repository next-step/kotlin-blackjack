package blackject.model

/**
 * 게임 결과 관리
 * */
object GameResult {

    fun getWinNumber(maxInt: Int, numbers: List<Int>): Int =
        numbers
            .filter { it <= maxInt }.maxOrNull()!!
}
