package introduce

@DslMarker
annotation class IntroduceMaker

@IntroduceMaker
interface IntroduceBuilder<T> {
    fun build(): T
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()
