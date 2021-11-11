package dsl

class Company(val value: String) {
    init {
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException()
        }
    }
}
