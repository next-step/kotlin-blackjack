package blackject.model

import blackject.model.card.Cards
import blackject.model.card.CardsDeck

/**
 * 참가자들 관리 클래스
 * */
class Participant(
    private val dealer: Dealer,
    private val persons: List<Person>,
) {
    fun getAllPerson(): List<Player> = persons.plus(dealer)
    fun getPerson(): List<Person> = persons

    private fun sumOfAllPersonAmount(): Int = persons.sumOf { it.getBetAmount().toInt() }
    private fun sumOfPlusProfit(): Int = persons.filter { it.hasPlusProfit() }.sumOf { it.getProfit() }

    private fun winnerScore(): Int =
        getAllPerson()
            .map { it.getScore() }
            .filter { it <= Cards.BLACK_JACK_SUM }.maxOrNull()!!

    fun giveCards(cardCount: Int, print: (Player) -> Unit) {
        persons.plus(dealer).forEach {
            it.giveCard(CardsDeck.takeCard(cardCount))
            print.invoke(it)
        }
    }

    fun setGameResult(): GameResult {
        getAllPerson().forEach {
            it.calculateGameResult(winnerScore(), dealer.isBust(), dealer.isBlackJack())
        }

        return GameResult(
            hashMapOf<Player, Int>().apply {
                putAll(persons.associateWith { it.getProfit() })
                put(dealer, dealer.calculateProfit(sumOfAllPersonAmount(), sumOfPlusProfit()))
            }
        )
    }

    fun isTakeMoreCard(ask: (Dealer) -> Unit) {
        if (!dealer.canTakeMoreCard()) return
        ask.invoke(dealer)
    }

    companion object {
        private const val DELIMITER_NAME = ","
        fun addPerson(name: String?): Participant {
            require(!name.isNullOrEmpty())
            return name.split(DELIMITER_NAME)
                .map { Person(name = it) }
                .let { Participant(Dealer(), it) }
        }
    }
}
