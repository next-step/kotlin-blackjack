package dsl

@DslMarker
annotation class PersonDsl

@PersonDsl
abstract class DslBuilder<T> {

    abstract fun build(): T
}
