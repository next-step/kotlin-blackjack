package blackject.model

import blackject.model.card.CardsDeck

/**
 * 참가자들 관리 클래스
 * */
class Participant(
    val dealer: Dealer,
    val persons: List<Person>
) {

    fun getAllPerson(): List<Person> = persons.plus(dealer)

    fun giveCards(cardCount: Int, print: (Person) -> Unit) {
        persons.plus(dealer).forEach {
            it.giveCard(CardsDeck.takeCard(cardCount))
            print.invoke(it)
        }
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
