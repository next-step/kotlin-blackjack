package blackjack.domain.shuffle

interface Shuffler<T> {

    fun shuffled(list: List<T>): List<T>
}
