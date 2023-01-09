package blackjack.domain

class Game(private val _gamerList: List<Gamer>, private val _dealer: Dealer = Dealer()) {

    val dealer: Dealer
        get() = _dealer

    val gamerList: List<Gamer>
        get() = _gamerList

    val participant = gamerList + dealer

    fun getParticipantNames(): List<String> {
        return participant.map { it.name }
    }

    fun changeResult() {
        gamerList.forEach { gamer -> gamer.changeState(gamer.isWin(dealer)) }
        dealer.changeResult(gamerList)
    }
}
