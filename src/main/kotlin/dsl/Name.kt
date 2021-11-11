package dsl

class Name(val value: String) {
    init {
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException()
        }
    }
}
