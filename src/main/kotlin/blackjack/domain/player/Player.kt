package blackjack.domain.player

class Player(
    val player: Participant,
    val betAmount: Int,
) : CardFunction by player {

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
