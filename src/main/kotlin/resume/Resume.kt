package resume

class Resume {
    companion object {
        fun introduce(initialize: PersonBuilder.() -> Unit): Person {
            return PersonBuilder().apply(initialize).build()
        }
    }
}
