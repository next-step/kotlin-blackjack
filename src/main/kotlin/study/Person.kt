package study

data class Person(
    val name: String,
    val company: String,
) {

    class Builder {

        private var name: String? = null
        private var company: String? = null

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun build(): Person {
            return Person(
                name.orEmpty(),
                company.orEmpty(),
            )
        }
    }
}

fun introduce(block: Person.Builder.() -> Unit): Person {
    return Person.Builder().apply(block).build()
}
