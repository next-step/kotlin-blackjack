package study

/**
 * block 은 PersonBuilder 의 함수를 인자로 받음
 */
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
