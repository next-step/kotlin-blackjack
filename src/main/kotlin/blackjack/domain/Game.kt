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
        gamerList.forEach { gamer -> gamer.changeState(dealer) }
    }

    fun settle() {
        gamerList.forEach { gamer ->
            when (gamer.state) {
                State.WIN -> {
                    dealer.changeMoney(gamer.money.unaryMinus())
                    gamer.changeMoney(0.0)
                }
                State.BLACKJACK -> {
                    dealer.changeMoney((gamer.money + gamer.money / 2).unaryMinus())
                    gamer.changeMoney(gamer.money / 2)
                }
                State.LOSE -> {
                    dealer.changeMoney(gamer.money)
                    gamer.changeMoney(gamer.money.unaryMinus() * 2)
                }
                else -> {
                    dealer.changeMoney(0.0)
                    gamer.changeMoney(0.0)
                }
            }
        }
    }
}
