package blackjack.support

import blackjack.domain.Score

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfScore")
inline fun <T> Iterable<T>.sumOf(selector: (T) -> Score): Score {
    var sum: Score = Score.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
