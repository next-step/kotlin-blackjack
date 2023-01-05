package blackjack.domain

class Game(private val _dealer: Dealer, private val _gamerList: List<Gamer>) {

    val dealer: Dealer
        get() = _dealer

    val gamerList: List<Gamer>
        get() = _gamerList

    constructor(gamerList: List<Gamer>) : this(Dealer(), gamerList)

    fun getParticipant(): List<Person> = buildList {
        add(dealer)
        gamerList.forEach { add(it) }
    }

    fun getParticipantNames(): List<String> {
        return buildList {
            add(dealer.name)
            gamerList.forEach { add(it.name) }
        }
    }

    fun changeResult() {
        gamerList.forEach { gamer -> gamer.changeState(gamer.isWin(dealer)) }
        dealer.changeResult(gamerList)
    }
}
