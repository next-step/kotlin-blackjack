package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    // PersonBuilder 인스턴스를 생성하여 block()을 호출하도록 했다.
    // 1차 : PersonBuilder().block() -> Unit으로 리턴하기때문에 바로 build()를 호출할 수 없다.
    // 2차 : return PersonBuilder().apply {
    //      this.block()
    //  }.build()
    return PersonBuilder().apply(block).build()
}
