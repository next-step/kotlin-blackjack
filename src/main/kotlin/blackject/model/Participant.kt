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
            val list = name.split(DELIMITER_NAME).map { Person(name = it) }
            return Participant(list)
        }
    }
}
