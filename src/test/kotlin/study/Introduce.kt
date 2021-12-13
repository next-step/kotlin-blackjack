package study

fun introduce(initializer: PersonBuilder.() -> Unit): Person { // Person class 함수만 들어올 수 있다 , Person.()
    return PersonBuilder().apply(initializer).build()
}
