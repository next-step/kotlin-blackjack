package blackject.model

/**
 * 참가자들 관리 클래스
 * */
class Participant(
    val dealer: Dealer,
    val persons: List<Person>
) {

    fun getAllPerson(): List<Person> = persons.plus(dealer)

    companion object {
        private const val DELIMITER_NAME = ","
        fun addPerson(name: String?): Participant {
            require(!name.isNullOrEmpty())
            val list = mutableListOf<Person>().apply {
                addAll(
                    name
                        .split(DELIMITER_NAME)
                        .map { Person(type = PersonType.NORMAL, name = it) }
                )
            }

            return Participant(Dealer(), list)
        }
    }
}
