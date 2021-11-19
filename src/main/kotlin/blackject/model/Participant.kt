package blackject.model

/**
 * 참가자들 관리 클래스
 * */
class Participant(
    val persons: List<Person>
) {

    companion object {
        private const val DELIMITER_NAME = ","
        fun addPerson(name: String?): Participant {
            require(!name.isNullOrEmpty())
            val list = mutableListOf(Person(type = PersonType.DEALER, name = PersonType.DEALER.personName)).apply {
                addAll(
                    name
                        .split(DELIMITER_NAME)
                        .map { Person(type = PersonType.NORMAL, name = it) }
                )
            }

            return Participant(list)
        }
    }
}
