package blackject.model

import blackject.model.card.Cards
import blackject.model.card.CardsDeck

/**
 * 참가자들 관리 클래스
 * */
class Participant(
    val dealer: Dealer,
    val persons: List<Person>,
) {
    fun getAllPerson(): List<Person> = persons.plus(dealer)

    fun sumOfPlusProfit(): Int = persons.filter { it.hasPlusProfit() }.sumOf { it.getProfit() }

    fun winnerScore(): Int =
        getAllPerson()
            .map { it.getScore() }
            .filter { it <= Cards.BLACK_JACK_SUM }.maxOrNull()!!

    fun giveCards(cardCount: Int, print: (Person) -> Unit) {
        persons.plus(dealer).forEach {
            it.giveCard(CardsDeck.takeCard(cardCount))
            print.invoke(it)
        }
    }

    fun inputBetAmountByPerson(print: (Person) -> String?) {
        persons.forEach {
            val amount = print.invoke(it)
            it.inputBetMoney(amount?.toDoubleOrNull())
        }
        dealer.inputBetMoney(persons.sumOf { it.getBetAmount() })
    }

    fun setGameResult(): GameResult {
        getAllPerson().forEach {
            it.calculateGameResult(winnerScore(), dealer.isBust(), dealer.isBlackJack())
        }

        return GameResult(
            hashMapOf<Person, Int>().apply {
                putAll(persons.associateWith { it.getProfit() })
                put(dealer, dealer.calculateProfit(persons.sumOf { it.getBetAmount().toInt() }, sumOfPlusProfit()))
            }
        )
    }

    fun askMoreCard(ask: (Person) -> Unit) {
        persons.forEach { ask.invoke(it) }
    }

    fun isTakeMoreCard(ask: (Dealer) -> Unit) {
        if (!dealer.canTakeMoreCard()) return
        ask.invoke(dealer)
    }

    fun print(print: (Person) -> Unit) {
        getAllPerson().forEach(print)
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
