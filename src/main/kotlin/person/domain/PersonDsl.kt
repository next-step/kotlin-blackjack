package person.domain

object PersonDsl {
    fun introduce(function: () -> Unit): Person {
        return Person()
    }
}
