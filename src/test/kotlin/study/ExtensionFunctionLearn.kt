package study

import io.kotest.core.spec.style.StringSpec

class ExtensionFunctionLearn : StringSpec({
    "ExtensionFunction(확장함수) 를 학습한다" {
        val countWords = "확장함수는 고랭의 그것과 비슷합니다".countWords()
        print(countWords)
    }
})

fun String.countWords(): Int {
    return this.split("\\s+".toRegex()).size
}
