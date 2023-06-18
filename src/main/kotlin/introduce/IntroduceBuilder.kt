package introduce

@IntroduceMaker
interface IntroduceBuilder<T> {
    fun build(): T
}

@DslMarker
annotation class IntroduceMaker
