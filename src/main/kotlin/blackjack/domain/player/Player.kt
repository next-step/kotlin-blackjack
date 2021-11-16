package blackjack.domain.player

class Player(
    val name: String,
) {

    val player = Participant(name)

    init {
        require(name.isNotBlank()) { "이름은 공백제외 1글자 이상이어야 합니다." }
    }

    var resultStatus = ResultStatus.UNKNOWN
        private set

    fun isCardReceiveAble(): Boolean {
        return player.getCardSum() < DEADLINE
    }

    fun determineWinOrLose(winOrLose: ResultStatus) {
        resultStatus = winOrLose
    }

    companion object {
        private const val DEADLINE = 21
    }
}
