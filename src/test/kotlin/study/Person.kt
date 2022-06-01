package study

data class Person(val name: String) {

    companion object {

        inline fun introduce(block: PersonBuilder.() -> Unit): Person =
            PersonBuilder()
                .apply(block)
                .build()

        class PersonBuilder {
            private lateinit var name: String

            fun name(value: String) {
                name = value
            }

            fun build() = Person(name)
        }
    }
}
