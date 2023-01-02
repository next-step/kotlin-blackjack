package blackjack.domain

class Participant(val persons: List<Person>) {

    constructor(expression: String) : this(
        buildList<Person> {
            add(Dealer())
            expression.split(",").forEach { add(Gamer(it)) }
        }
    )

    fun getGamerList(): List<Gamer> = persons.filterIsInstance<Gamer>()

    fun getDealer(): Dealer = persons.filterIsInstance<Dealer>()[0]

    fun getParticipantNames(): List<String> = persons.map { it.name }

    fun checkResult() {
        val dealer: Dealer = getDealer()
        val gamerList: List<Gamer> = getGamerList()
        gamerList.forEach { gamer ->  gamer.changeState(gamer.isWin(dealer)) }
        dealer.checkResult(gamerList)
    }
}
