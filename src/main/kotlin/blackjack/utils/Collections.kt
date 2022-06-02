package blackjack.utils

// cartesian product
operator fun <T, U> Collection<T>.times(other: Collection<U>): List<Pair<T, U>> {
    return this.flatMap { lhsElem -> other.map { rhsElem -> lhsElem to rhsElem } }
}

operator fun <T, U> Array<T>.times(other: Array<U>) = this.toList().times(other.toList())
